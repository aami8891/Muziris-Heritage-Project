package com.example.apaar97.translate;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.shivtechs.mediaplayemodule.AudioPlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.apaar97.translate.GlobalVars.BASE_REQ_URL;
import static com.example.apaar97.translate.GlobalVars.DEFAULT_LANG_POS;
import static com.example.apaar97.translate.GlobalVars.LANGUAGE_CODES;
import static com.shivtechs.mediaplayemodule.AudioPlayer.MODE_PATH;

public class TranslationActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    public static final String LOG_TAG = TranslationActivity.class.getName();
    private static final int REQ_CODE_SPEECH_INPUT = 1;

    private TextToSpeech mTextToSpeech;                     //    Text to Speech Engine
    private Spinner mSpinnerLanguageFrom;                   //    Dropdown list for selecting base language (From)
    private Spinner mSpinnerLanguageTo;                     //    Dropdown list for selecting translation language (To)
    private String mLanguageCodeFrom = "en";                //    Language Code (From)
    private String mLanguageCodeTo = "en";                  //    Language Code (To)
    private ImageView mImageSpeak;                          //    Speak button to read out translated text (Text to Speech)
    private EditText mTextInput;                            //    Input text ( in From language )
    private TextView mTextTranslated;                       //    Output Translated text ( in To language )
    private Dialog process_tts;                             //    Dialog box for Text to Speech Engine Language Switch
    HashMap<String, String> map = new HashMap<>();
    volatile boolean activityRunning;                       //    To track status of current activity

    private String k;
    private TextView et;
    private View v;
    private String mMessage,mMessageartifact;
    public String[] images = new String[100];
    private String stringaudio;
    private int len;
    Context context1,context2;
    Adapter adapter;
    List<Model> models;
    ViewPager viewPager;
    Activity activity1;
    FloatingActionButton actionButton;
    TextToSpeech tts;
    String desc;
    String st = null;
    String st2 = null;
    TextView description;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        //-----------------------------------------------------------------------------

        et = findViewById(R.id.textView2);

        context2=getBaseContext();
        tts = new TextToSpeech(getApplication(), this);
        new Thread(new Runnable() {
            public void run() {
                try {
                    postRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        ImageView icon = new ImageView(getBaseContext()); // Create an icon
        icon.setImageDrawable(getResources().getDrawable(R.drawable.media) );

        actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                .build();
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        // repeat many times:
        ImageView itemIcon = new ImageView(getBaseContext());
        itemIcon.setImageDrawable(getResources().getDrawable(R.drawable.audio)  );
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();

        ImageView itemIcon1 = new ImageView(getBaseContext());
        itemIcon1.setImageDrawable(getResources().getDrawable(R.drawable.texttospeech)  );
        SubActionButton button2 = itemBuilder.setContentView(itemIcon1).build();

        ImageView itemIcon2 = new ImageView(getBaseContext());
        itemIcon2.setImageDrawable(getResources().getDrawable(R.drawable.video)  );
        SubActionButton button3 = itemBuilder.setContentView(itemIcon2).build();

        ImageView itemIcon3 = new ImageView(getBaseContext());
        itemIcon2.setImageDrawable(getResources().getDrawable(R.drawable.imag)  );
        SubActionButton button4 = itemBuilder.setContentView(itemIcon3).build();

        final FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)

                .attachTo(actionButton)
                .build();


        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vi) {
                Toast.makeText(getBaseContext(),"Audio playing",Toast.LENGTH_SHORT).show();

                String FILE_PROVIDER_NAME = "com.shivtechs.provider" ;
                //For playing the audio file from the given path
                stringaudio = "https://mangoess.000webhostapp.com/MangoFolder/Njandukalude%20Naattil%20Oridavela%20_%20Enthaavo%20Song%20Vid%20-%20128K%20MP3.mp3";
                AudioPlayer player2 = new AudioPlayer(this,FILE_PROVIDER_NAME,getParent().isFinishing(),getParent().getFragmentManager(),stringaudio,MODE_PATH);
                //For playing the audio file from the android resource
                //    AudioPlayer player = new AudioPlayer(getActivity(),getActivity().isFinishing(),getActivity().getSupportFragmentManager(),R.raw.guitar,MODE_RESOURCE);


                actionMenu.close(true);

            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Text to Speech",Toast.LENGTH_SHORT).show();
                Log.e("languages",tts.getAvailableLanguages().toString());
                tts.setPitch(1f);
                tts.setSpeechRate(1f);
                tts.speak(st.substring(10,1250), TextToSpeech.QUEUE_FLUSH, null, null);
                actionMenu.close(true);
            }





        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Video Files",Toast.LENGTH_SHORT).show();
                actionMenu.close(true);
            }





        });

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Video Files",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    public void run() {
                        getMoreImagePostMethod();

                    }
                }).start();
                actionMenu.close(true);
            }
        });




        //*******************************************************************************************************
        activityRunning=true;
        TextView mEmptyTextView = (TextView) findViewById(R.id.empty_view_not_connected);
        mSpinnerLanguageFrom = (Spinner) findViewById(R.id.spinner_language_from);
        mSpinnerLanguageTo = (Spinner) findViewById(R.id.spinner_language_to);
        Button mButtonTranslate = (Button) findViewById(R.id.button_translate);         //      Translate button to translate text
        ImageView mImageSwap = (ImageView) findViewById(R.id.image_swap);               //      Swap Language button to swap languages
        ImageView mImageListen = (ImageView) findViewById(R.id.image_listen);           //      Mic button for Speech to text
        mImageSpeak = (ImageView) findViewById(R.id.image_speak);
        ImageView mClearText = (ImageView) findViewById(R.id.clear_text);               //      Clear button to clear text fields
        mTextInput = (EditText) findViewById(R.id.text_input);
        mTextTranslated = (TextView) findViewById(R.id.text_translated);
        mTextTranslated.setMovementMethod(new ScrollingMovementMethod());
        process_tts = new Dialog(TranslationActivity.this);
        process_tts.setContentView(R.layout.dialog_processing_tts);
        process_tts.setTitle(getString(R.string.process_tts));
        TextView title = (TextView) process_tts.findViewById(android.R.id.title);
        // title.setSingleLine(false);
        mTextToSpeech = new TextToSpeech(this, this);

        //  CHECK INTERNET CONNECTION
        if (!isOnline()) {
            mEmptyTextView.setVisibility(View.VISIBLE);
        } else {
            mEmptyTextView.setVisibility(View.GONE);
            //  GET LANGUAGES LIST
            new GetLanguages().execute();
            //  SPEECH TO TEXT
            mImageListen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, mLanguageCodeFrom);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
                    try {
                        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                    } catch (ActivityNotFoundException a) {
                        Toast.makeText(getApplicationContext(), getString(R.string.language_not_supported), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //  TEXT TO SPEECH
            mImageSpeak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    speakOut();
                }
            });
            //  TRANSLATE
            mButtonTranslate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
              //      String input = mTextInput.getText().toString();
              //      String input = desc.substring(100,150); desc.replaceAll("[^A-Za-z0-9]", "");
                //    String input = "A paragraph is a series of related sentences developing a central idea, called the topic. Try to think about paragraphs in terms of thematic unity: a paragraph is a sentence or a group of sentences that supports one central, unified idea. Paragraphs add one idea at a time to your broader argument.";
                    String input =  st2;
                    new TranslateText().execute(input);
                }
            });
            //  SWAP BUTTON
            mImageSwap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temp = mLanguageCodeFrom;
                    mLanguageCodeFrom = mLanguageCodeTo;
                    mLanguageCodeTo = temp;
                    int posFrom = mSpinnerLanguageFrom.getSelectedItemPosition();
                    int posTo = mSpinnerLanguageTo.getSelectedItemPosition();
                    mSpinnerLanguageFrom.setSelection(posTo);
                    mSpinnerLanguageTo.setSelection(posFrom);
                    String textFrom = mTextInput.getText().toString();
                    String textTo = mTextTranslated.getText().toString();
                    mTextInput.setText(textTo);
                    mTextTranslated.setText(textFrom);
                }
            });
            //  CLEAR TEXT
            mClearText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTextInput.setText("");
                    mTextTranslated.setText("");
                }
            });
            //  SPINNER LANGUAGE FROM
            mSpinnerLanguageFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mLanguageCodeFrom = LANGUAGE_CODES.get(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Toast.makeText(getApplicationContext(), "No option selected", Toast.LENGTH_SHORT).show();
                }
            });
            //  SPINNER LANGUAGE TO
            mSpinnerLanguageTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mLanguageCodeTo = LANGUAGE_CODES.get(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Toast.makeText(getApplicationContext(), "No option selected", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    //  CHECK INTERNET CONNECTION
    public  boolean isOnline()
    {   try {
            ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return false;
    }
    //  RESULT OF SPEECH INPUT
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    /*
                            Dialog box to show list of processed Speech to text results
                            User selects matching text to display in chat
                     */
                    final Dialog match_text_dialog = new Dialog(TranslationActivity.this);
                    match_text_dialog.setContentView(R.layout.dialog_matches_frag);
                    match_text_dialog.setTitle(getString(R.string.select_matching_text));
                    ListView textlist = (ListView)match_text_dialog.findViewById(R.id.list);
                    final ArrayList<String> matches_text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,matches_text);
                    textlist.setAdapter(adapter);
                    textlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            mTextInput.setText(matches_text.get(position));
                            match_text_dialog.dismiss();
                        }
                    });
                    match_text_dialog.show();
                    break;
                }
            }
        }
    }
    //  INITIALISE TEXT TO SPEECH ENGINE
    @Override
    public void onInit(int status) {
        Log.e("Inside----->", "onInit");
        if (status == TextToSpeech.SUCCESS) {
            int result = mTextToSpeech.setLanguage(new Locale("en"));
            if (result == TextToSpeech.LANG_MISSING_DATA) {
                Toast.makeText(getApplicationContext(), getString(R.string.language_pack_missing), Toast.LENGTH_SHORT).show();
            } else if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(getApplicationContext(), getString(R.string.language_not_supported), Toast.LENGTH_SHORT).show();
            }
            mImageSpeak.setEnabled(true);
            mTextToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                    Log.e("Inside","OnStart");
                    process_tts.hide();
                }
                @Override
                public void onDone(String utteranceId) {
                }
                @Override
                public void onError(String utteranceId) {
                }
            });
        } else {
            Log.e(LOG_TAG,"TTS Initilization Failed");
        }
    }
    //  TEXT TO SPEECH ACTION
    @SuppressWarnings("deprecation")
    private void speakOut(){
        int result = mTextToSpeech.setLanguage(new Locale(mLanguageCodeTo));
        Log.e("Inside","speakOut "+mLanguageCodeTo+" "+result);
        if (result == TextToSpeech.LANG_MISSING_DATA ){
            Toast.makeText(getApplicationContext(),getString(R.string.language_pack_missing),Toast.LENGTH_SHORT).show();
            Intent installIntent = new Intent();
            installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(installIntent);
        } else if(result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Toast.makeText(getApplicationContext(),getString(R.string.language_not_supported),Toast.LENGTH_SHORT).show();
        } else {
         //   String textMessage = mTextTranslated.getText().toString();
            String textMessage = mTextTranslated.getText().toString();
            process_tts.show();
            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UniqueID");
            mTextToSpeech.speak(textMessage.substring(0,400), TextToSpeech.QUEUE_FLUSH, map);
        }
    }
    //  WHEN ACTIVITY IS PAUSED
    @Override
    protected void onPause() {
        if(mTextToSpeech!=null){
            mTextToSpeech.stop();
        }
        super.onPause();
    }
    //  WHEN ACTIVITY IS DESTROYED
    @Override
    public void onDestroy() {
        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }
        activityRunning=false;
        process_tts.dismiss();
        super.onDestroy();
    }
    //  SUBCLASS TO TRANSLATE TEXT ON BACKGROUND THREAD
    private class TranslateText extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... input) {
            Uri baseUri = Uri.parse(BASE_REQ_URL);
            Uri.Builder uriBuilder = baseUri.buildUpon();
            uriBuilder.appendPath("translate")
                    .appendQueryParameter("key",getString(R.string.API_KEY))
                    .appendQueryParameter("lang",mLanguageCodeFrom+"-"+mLanguageCodeTo)
                    .appendQueryParameter("text",input[0]);
            Log.e("String Url ---->",uriBuilder.toString());
            return QueryUtils.fetchTranslation(uriBuilder.toString());
        }
        @Override
        protected void onPostExecute(String result) {
            if(activityRunning) {
                mTextTranslated.setText(result);
                description.setText(result);
            }
        }
    }
    //  SUBCLASS TO GET LIST OF LANGUAGES ON BACKGROUND THREAD
    private class GetLanguages extends AsyncTask<Void,Void,ArrayList<String>>{
        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            Uri baseUri = Uri.parse(BASE_REQ_URL);
            Uri.Builder uriBuilder = baseUri.buildUpon();
            uriBuilder.appendPath("getLangs")
                    .appendQueryParameter("key",getString(R.string.API_KEY))
                    .appendQueryParameter("ui","en");
            Log.e("String Url ---->",uriBuilder.toString());
            return QueryUtils.fetchLanguages(uriBuilder.toString());
        }
        @Override
        protected void onPostExecute(ArrayList<String> result) {
            if (activityRunning) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(TranslationActivity.this, android.R.layout.simple_spinner_item, result);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinnerLanguageFrom.setAdapter(adapter);
                mSpinnerLanguageTo.setAdapter(adapter);
                //  SET DEFAULT LANGUAGE SELECTIONS
                mSpinnerLanguageFrom.setSelection(DEFAULT_LANG_POS);
                mSpinnerLanguageTo.setSelection(DEFAULT_LANG_POS);
            }
        }
    }
    //******************************************************************************************
    public void postRequest() throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "http://muzirisdemot8.herokuapp.com/ipa/getartifactfromqr";//http://muzirisdemot8.herokuapp.com/ipa/getartifactfromqr";

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
        desc = obj.getString("englishtext");
        Log.e("msggg",name);

        description =  findViewById(R.id.textView2);

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setText(description,desc);
            }
        });




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

    private void setText(TextView text,final String value){

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        byte[] decode = Base64.decode(value,Base64.DEFAULT);

        try {
            st = new String (decode,"UTF-8");
            st2 = new String (decode,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("msggg",e.toString());
            e.printStackTrace();
        }
        text.setText(st);

        Glide.with(context2)
                .load("https://mangoess.000webhostapp.com/MangoFolder/ser.jpg")//"https://files.000webhost.com/handler.php?action=download?action=download&path=%2FImages%2Fser.jpg
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView);
////

    }

    public void getMoreImagePostMethod(){
        context1 = getBaseContext();

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "http://muzirisdemot8.herokuapp.com/ipa/extraimageartefact";

        OkHttpClient client = new OkHttpClient();

        JSONObject postdata = new JSONObject();
        try {
            postdata.put("id", "1201");
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

                mMessageartifact = response.body().string();
                Log.e("yyyyyy2", mMessageartifact.toString());
                try {

                    JSONArray jsonArr = new JSONArray(mMessageartifact);
                    len = jsonArr.length();
                    Log.e("yyyyyy",String.valueOf(len));

                    for(int i = 0 ;i<len;i++){
                        images[i] = jsonArr.getString(i);
                    }
                  runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showthesliderimage(images,len);

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        });

    }

    public void showthesliderimage(String[] images, int lenth){

        Toast.makeText(getBaseContext(), String.valueOf(lenth),Toast.LENGTH_LONG).show();
        models = new ArrayList<>();
        for(int i = 0; i< lenth; i++){
            //  String urls = "https://mangoess.000webhostapp.com/MangoFolder/"+images[i];
            models.add(new Model(images[i], "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
        }

//        Glide.with(context2)
//                .load("https://mangoess.000webhostapp.com/MangoFolder/ser.jpg")//"https://files.000webhost.com/handler.php?action=download?action=download&path=%2FImages%2Fser.jpg
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(imageView);
//
//        models.add(new Model(R.drawable.brochure, "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
//        models.add(new Model(R.drawable.poster, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
//        models.add(new Model(R.drawable.namecard, "Namecard", "Business cards are cards bearing business information about a company or individual."));
        adapter = new Adapter(models, getBaseContext(),v);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);


    }
    //******************************************************************************************

}