package com.example.chmarax.logregform;

import android.animation.ArgbEvaluator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompleteArtifacts2 extends Fragment {
    private Handler mHandler = new Handler(Looper.getMainLooper());

    String data;

    ViewPager viewPager2;
    Adapter2 adapter;
    List<Model2> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
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
    View v;
    public CompleteArtifacts2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_complete_artifacts2, container, false);
        getAsyncCall();









        return v;
    }
    public void getAsyncCall() {
        OkHttpClient httpClient = new OkHttpClient();
        String url = "https://muzirisdemot2.herokuapp.com/ipa/museums";
        Request request = new Request.Builder()
                .url(url)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                //  resposeString = "fail";

                Log.e("message", "error in getting response using async okhttp call");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                data = response.body().string();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            abcd(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });



                if (!response.isSuccessful()) {
                    throw new IOException("Error response " + response);
                }


            }
        });


    }


    public void abcd(String resposeString) throws JSONException {
        //  Object obj = resposeString;
        final Object obb[] =  new Object[10];
        JSONArray jsonArr = new JSONArray(resposeString);
        len = jsonArr.length();
        for(int i = 0 ;i<len;i++){
            obb[i] = jsonArr.get(i);
            JSONObject jo = (JSONObject) obb[i];
            lat[i] = jo.get("lat").toString();
            lon[i] = jo.get("lon").toString();
            museumsid[i] = jo.get("id").toString();
            place[i] = jo.get("place").toString();
            desc[i] = jo.get("desc").toString();
            mainimg[i] = jo.get("mainimg").toString();
            pic1[i] = jo.get("mainimgContentType").toString();
            contact[i] = jo.get("contact").toString();
            avgtime[i] = jo.get("avgtime").toString();
            highlight[i] = jo.get("highlight").toString();
            name[i] = jo.get("name").toString();
            priority[i] = jo.get("priority").toString();

            Log.e("Msd",museumsid[i]);
//
//       //     tv.setText(tv.getText() +" "+lat[i]+"   "+lon[i]+"   \n");

        }

        Log.e("Msd",String.valueOf(len));
        models = new ArrayList<>();
        for(int i =0 ; i < len ; i++){
            String pureBase64Encoded =  mainimg[i];
            byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            models.add(new Model2(decodedByte, museumsid[i], place[i]));
        }
//        models.add(new Model(R.drawable.brochure, museumsid[1], place[1]));
//        models.add(new Model(R.drawable.brochure, museumsid[1], place[1]));
//        models.add(new Model(R.drawable.brochure, "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
//        models.add(new Model(R.drawable.poster, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
//        models.add(new Model(R.drawable.namecard, "Namecard", "Business cards are cards bearing business information about a company or individual."));

        adapter = new Adapter2(models, getContext());

        viewPager2 = v.findViewById(R.id.viewPager2);
        viewPager2.setAdapter(adapter);
        viewPager2.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

        viewPager2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager2.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager2.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}

