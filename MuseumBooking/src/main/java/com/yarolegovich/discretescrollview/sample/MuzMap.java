package com.yarolegovich.discretescrollview.sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MuzMap extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    public String resposeString=null;
    public String[] lat = new String[10];
    public String[] lon = new String[10];
    public int len;
    public ViewGroup viewGroup;
    public int[] fid = new int[10];
    public String[] museumsid = new String[10];
    public String[] place = new String[10];
    public String[] desc = new String[10];
    public String[] mainimg = new String[10];
    public String[] pic1 = new String[10];
    public String[] pic2 = new String[10];
    public String[] pic3 = new String[10];
    public String[] contact = new String[10];
    public String[] avgtime = new String[10];
    public String[] highlight = new String[10];
    public String[] workingtime = new String[10];
    public String[] name = new String[10];
    public String[] priority = new String[10];
    //    public String lat = null;
//    public String lon = null;
    public Float flat;
    public Float flet;

    GoogleMap map;
    TextView tv;
    AlertDialog.Builder builder;
    //Button bt;
    public MuzMap() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_muz_map, container, false);
        tv = (TextView) rootView.findViewById(R.id.textView);
      //  bt = (Button) rootView.findViewById(R.id.button);
             viewGroup = rootView.findViewById(android.R.id.content);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
       mapFragment.getMapAsync(MuzMap.this);
        return rootView;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        getAsyncCall();



//        LatLng Mark = new LatLng(flat, flet);
//      //  LatLng Home = new LatLng( 9.540094, 76.331446);
//
//        map.addMarker(new MarkerOptions()
//
//                .position(Mark).title("Mark"));



        //  map.moveCamera(CameraUpdateFactory.newLatLng(Infopark));



    }


    public void abcd() throws JSONException {
        //  Object obj = resposeString;
        final Object obb[] =  new Object[10];
        JSONArray jsonArr = new JSONArray(resposeString);
        len = jsonArr.length();
        for(int i = 0 ;i<len;i++){
            obb[i] = jsonArr.get(i);
            JSONObject jo = (JSONObject) obb[i];
            lat[i] = jo.get("lat").toString();
            lon[i] = jo.get("lon").toString();

            museumsid[i] = jo.get("museumsid").toString();
            place[i] = jo.get("place").toString();
            desc[i] = jo.get("desc").toString();
            mainimg[i] = jo.get("mainimg").toString();
            pic1[i] = jo.get("pic1").toString();
            pic2[i] = jo.get("pic2").toString();
            pic3[i] = jo.get("pic3").toString();
            contact[i] = jo.get("contact").toString();
            avgtime[i] = jo.get("avgtime").toString();
            highlight[i] = jo.get("highlight").toString();
            name[i] = jo.get("name").toString();
            priority[i] = jo.get("priority").toString();


            tv.setText(tv.getText() +" "+lat[i]+"   "+lon[i]+"   \n");

        }
        LatLng Mark[] = new LatLng[10];
        for(int i =0 ; i< len; i++){
            flat = Float.parseFloat(lat[i]);
            flet = Float.parseFloat(lon[i]);
            Mark[i] = new LatLng(flat, flet);
            //  LatLng Home = new LatLng( 9.540094, 76.331446);

            map.addMarker(new MarkerOptions()

                    .position(Mark[i]).title("Mark"+(i+1)));

        }

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng( flat, flet))
                .zoom(12)
                .bearing(0)
                .tilt(45)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
        map.setOnMarkerClickListener(this);
        //  System.out.println(jsonArr.length());
        //  JSONArray jsonarray = (JSONArray) obj;
        // Object obj2 = jsonArr.get(0);
        // JSONObject jo = (JSONObject) obj2;
        //  tv.setText(jo.get("name").toString());
//         lat = jo.get("lat").toString();
//         lon = jo.get("lon").toString();
//           flat = Float.parseFloat(lat);
//           flet = Float.parseFloat(lon);
//      //   tv.setText(lat+"   "+lon+"   "+len);
        //  txtString.setText(jsonArr.get(0).toString());
//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
//        mapFragment.getMapAsync(com.yarolegovich.discretescrollview.sample.MuzMap.this);
    }



    public void getAsyncCall() {
        OkHttpClient httpClient = new OkHttpClient();
        String url = "https://muzirisdemot1.herokuapp.com/ipa/museums";
        Request request = new Request.Builder()
                .url(url)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                resposeString = "fail";
                //Log.e(TAG, "error in getting response using async okhttp call");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //ResponseBody responseBody = response.body();
                String data = response.body().string();

                //  Object obj = data;
                //  JSONArray jsonarray = (JSONArray) obj;
                //   System.out.println(jsonarray.length());
                resposeString = data;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            abcd();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


                System.out.println(response);
                if (!response.isSuccessful()) {
                    throw new IOException("Error response " + response);
                }

                //Log.i(TAG,responseBody.string());
            }
        });

    }


    @Override
    public boolean onMarkerClick(Marker marker) {

     //   Toast.makeText(getContext(),marker.getTitle(),Toast.LENGTH_LONG).show();
        if (marker.getTitle().equals("Mark1"))
        {

            showCustomDialog(0);
            //handle click here
           // Toast.makeText(getContext(),"Museum 1 ",Toast.LENGTH_LONG).show();
        }

        if (marker.getTitle().equals("Mark2"))
        {
            //handle click here
            showCustomDialog(1);
            Toast.makeText(getContext(),"Museum 2",Toast.LENGTH_LONG).show();
        }
        if (marker.getTitle().equals("Mark3"))
        {
            //handle click here
            showCustomDialog(2);
            Toast.makeText(getContext(),"Museum 3",Toast.LENGTH_LONG).show();
        }
        if (marker.getTitle().equals("Mark4"))
        {
            //handle click here
            showCustomDialog(3);
            Toast.makeText(getContext(),"Museum 4",Toast.LENGTH_LONG).show();
        }
        return false;
    }


    private void showCustomDialog(int mid) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup


        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Guide List");

        builder.setMessage("name\t\t "+name[mid]+"\n"+ "museumsid\t\t"+
                museumsid[mid]+"\n"+"museumsid\t\t"+
                place[mid]+"\n"+"desc\t\t"+
                desc[mid]+"\n"+"mainimg\t\t"+
                mainimg[mid]+"\n"+"pic1\t\t"+
                pic1[mid]+"\n"+"pic2\t\t"+
                pic2[mid]+"\n"+"pic3\t\t"+
                pic3[mid]+"\n"+"contact\t\t"+
                contact[mid]+"\n"+"avgtime\t\t"+
                avgtime[mid]+"\n"+"highlight\t\t"+
                highlight[mid]+"\n"+"priority\t\t"+
                priority[mid]
        );









        builder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
