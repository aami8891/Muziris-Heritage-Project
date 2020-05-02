package com.example.chmarax.logregform;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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



    }

    private void setText(final TextView text,final String value){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }
}

