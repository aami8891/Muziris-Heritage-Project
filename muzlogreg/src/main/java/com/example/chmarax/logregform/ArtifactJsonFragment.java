package com.example.chmarax.logregform;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtifactJsonFragment extends Fragment {
    private String k;
    private TextView et;
    private View v;
    private String mMessage;
    private int len;
    private Activity activity = getActivity();
    public ArtifactJsonFragment() {

        // Required empty public constructor
    }

    public ArtifactJsonFragment(String s) {
        this.k = s;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_artifactjson, container,  false);

        et = v.findViewById(R.id.textView2);

        ImageView icon = new ImageView(getContext()); // Create an icon
        icon.setImageDrawable(getResources().getDrawable(R.drawable.media) );

        FloatingActionButton actionButton = new FloatingActionButton.Builder(getActivity())
                .setContentView(icon)
                .setPosition(FloatingActionButton.POSITION_RIGHT_CENTER)
                .build();
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(getActivity());
        // repeat many times:
        ImageView itemIcon = new ImageView(getContext());
        itemIcon.setImageDrawable(getResources().getDrawable(R.drawable.audio)  );
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();

        ImageView itemIcon1 = new ImageView(getContext());
        itemIcon1.setImageDrawable(getResources().getDrawable(R.drawable.texttospeech)  );
        SubActionButton button2 = itemBuilder.setContentView(itemIcon1).build();

        ImageView itemIcon2 = new ImageView(getContext());
        itemIcon2.setImageDrawable(getResources().getDrawable(R.drawable.video)  );
        SubActionButton button3 = itemBuilder.setContentView(itemIcon2).build();

        final FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(getActivity())
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)

                .attachTo(actionButton)
                .build();


        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Audio playing",Toast.LENGTH_SHORT).show();
                actionMenu.close(true);
            }





        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Text to Speech",Toast.LENGTH_SHORT).show();
                actionMenu.close(true);
            }





        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Video Files",Toast.LENGTH_SHORT).show();
                actionMenu.close(true);
            }





        });






        try {
                    postRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }



                // Stuff that updates the UI



//        new Thread(new Runnable() {
//            public void run() {
//
//            }
//        }).start();


      //  et.setText(k);

        return v;
    }

    public void postRequest() throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "http://muzirisdemot8.herokuapp.com/ipa/getartifactfromqr";

        OkHttpClient client = new OkHttpClient();

        JSONObject postdata = new JSONObject();
        try {
            postdata.put("qrcode", "100");
            //  postdata.put("password", "12345");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mMessage = response.body().string();
                Log.e("xxxxx", mMessage.substring(0, 100));
                try {
                    jsonparsing(mMessage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        });
    }

    public void jsonparsing(String message) throws JSONException {

       JSONObject obj = new JSONObject(message);
       String name = obj.getString("mainimg");
       final String desc = obj.getString("englishtext");
       Log.e("msggg",name);

       final TextView description =  v.findViewById(R.id.textView2);

     //   description.setText(desc.substring(0,30));
        setText(description,desc);
//        Glide.with(getContext())
//                .load("https://homepages.cae.wisc.edu/~ece533/images/barbara.bmp")
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(imageView);

      //  Picasso.get().load("http://placehold.it/120x120&text=image4").into(i);
       // Picasso.with(getContext()).load(imgUrl).fit().into(contentImageView);
//        try {
//            ImageView i = (ImageView) v.findViewById(R.id.imageView);
//            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL("https://homepages.cae.wisc.edu/~ece533/images/barbara.bmp").getContent());
//            i.setImageBitmap(bitmap);
//            i.setMaxHeight(25);
//            Log.e("imageerror","dsadsadas");
//        } catch (IOException e) {
//            Log.e("imageerror",e.toString());
//            e.printStackTrace();
//        }
     }

    private void setText(final TextView text,final String value){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
                byte[] decode = Base64.decode(value,Base64.DEFAULT);
                String st = null;
                try {
                     st = new String (decode,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Log.e("msggg",e.toString());
                    e.printStackTrace();
                }
                text.setText(st);
                Glide.with(getContext())

                .load("https://mangoess.000webhostapp.com/MangoFolder/ser.jpg")//"https://files.000webhost.com/handler.php?action=download?action=download&path=%2FImages%2Fser.jpg
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView);

            }
        });
    }
}

