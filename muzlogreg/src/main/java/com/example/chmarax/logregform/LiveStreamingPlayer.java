package com.example.chmarax.logregform;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class LiveStreamingPlayer extends Fragment  implements
        AdapterView.OnItemSelectedListener{
   
    MediaPlayer mPlayer;
    ImageView play,stop;
    String audioUrl;
    DatabaseReference myRef;
    TextView tvprepare;
    String[] language = { "Greek", "Fench", "Malayalam", "Spanish", "Tamil"};
    private Handler mHandler = new Handler(Looper.getMainLooper());
    long then;
    public LiveStreamingPlayer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Read from the database

        View v = inflater.inflate(R.layout.fragment_live_streaming_player, container, false);
        Spinner spin = (Spinner) v.findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(Objects.requireNonNull(getActivity()),android.R.layout.simple_spinner_item,language);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        play = v.findViewById(R.id.imgplaybutton);
        tvprepare = v. findViewById(R.id.tvPepare);
         play.setEnabled(false);
     //    play.setColorFilter(555);
        stop = v.findViewById(R.id.imgtopbutton);
         audioUrl = "https://mangoess.000webhostapp.com/MangoFolder/Njandukalude%20Naattil%20Oridavela%20_%20Enthaavo%20Song%20Vid%20-%20128K%20MP3.mp3";
         mPlayer = new MediaPlayer();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Timeofshowstarted");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue(Object.class);
                assert value != null;
                then = Long.parseLong(value.toString());
                Log.d("TAG", "Value is: " + value.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.e("evidey","111111111111111111111111111111111111111");

                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mPlayer.setDataSource(audioUrl);
                    mPlayer.prepare();
                    play.setEnabled(true);
                    tvprepare.setText("Play Now");
                    Log.e("evidey","222222222222222222222222222222222222222222222");
          //          Toast.makeText(getActivity(),"Song Prepared",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.e("evidey",e.toString()+"3333333333333333333333333333333333");
                    tvprepare.setText("No Show at this time come later.");
                }
//
            }
        });
//
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("evidey","111111111111111111111111111111111111111");
//
//                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                try {
//                    mPlayer.setDataSource(audioUrl);
//                    mPlayer.prepare();
//                    play.setEnabled(true);
//                    tvprepare.setText("Play Now");
//                    Log.e("evidey","222222222222222222222222222222222222222222222");
//                    //          Toast.makeText(getActivity(),"Song Prepared",Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception e){
//                    Log.e("evidey",e.toString()+"3333333333333333333333333333333333");
//                          tvprepare.setText("No Show at this time come later.");
//                }
//
//            }
//        });

//        new Thread(new Runnable() {
//            public void run() {
//               Log.e("evidey","111111111111111111111111111111111111111");
//
//                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                try {
//                    mPlayer.setDataSource(audioUrl);
//                    mPlayer.prepare();
//                    play.setEnabled(true);
//                    tvprepare.setText("Play Now");
//                    Log.e("evidey","222222222222222222222222222222222222222222222");
//          //          Toast.makeText(getActivity(),"Song Prepared",Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception e){
//                    Log.e("evidey",e.toString()+"3333333333333333333333333333333333");
//              //      tvprepare.setText("No Show at this time come later.");
//                }
//
//            }
//        }).start();



        play.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                play.setEnabled(false);

                try{

                    long now = Instant.now().toEpochMilli();
                    long diff = now - then;
                    mPlayer.seekTo(Integer.parseInt(String.valueOf(diff)));
                    mPlayer.start();
                    // Inform user for audio streaming
                    Toast.makeText(getContext(),"Playing",Toast.LENGTH_SHORT).show();
                } catch (IllegalArgumentException e){
                    e.printStackTrace();
                }catch (SecurityException e){
                    e.printStackTrace();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }

                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(getContext(),"Show has End",Toast.LENGTH_SHORT).show();
                         play.setEnabled(true);
                         tvprepare.setText("Show has End");
                    }
                });
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setEnabled(true);

                if(mPlayer.isPlaying()){
                    mPlayer.pause();
                }

            }
        });

        return  v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
