package com.niloy.varx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class TalkActivity extends AppCompatActivity {
    private ImageButton mic;
    private ImageButton send;
    private TextView text;
    private boolean isMicOn = false;
    private static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;

    private TextToSpeech tts;
    private ProgressBar progressBar;
    private EditText editText;
    private ImageView vibration;
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private LinearLayoutManager linearLayoutManager;

    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        initializeUI();
        checkPermission();
        initializeSpeechRecogniser();
        initializeTextToVoice();
        textToVoice();

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                text.setText("");
                text.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {
                vibration.setVisibility(View.GONE);
            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                mic.setImageResource(R.drawable.mic_off);
                mic.setBackgroundColor(getResources().getColor(R.color.lightGray));
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                text.setText(data.get(0));
                arrayList.add(data.get(0));
                tts.speak(data.get(0), TextToSpeech.QUEUE_FLUSH, null);
                linearLayoutManager.setReverseLayout(true);
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        mic = findViewById(R.id.mic);
        mic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mic.setImageResource(R.drawable.mic_off);
                    mic.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    speechRecognizer.stopListening();
                    vibration.setVisibility(View.VISIBLE);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mic.setImageResource(R.drawable.mic_on);
                    mic.setBackgroundColor(getResources().getColor(R.color.lightGray));
                    speechRecognizer.startListening(speechRecognizerIntent);
                    vibration.setVisibility(View.GONE);
                }
                return false;
            }
        });
//        mic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isMicOn){
//                    speechRecognizer.stopListening();
//                    mic.setImageResource(R.drawable.mic_off);
//                    mic.setBackgroundColor(getResources().getColor(R.color.lightGray));
//                    isMicOn = false;
//                }else {
//                    speechRecognizer.startListening(speechRecognizerIntent);
//                    mic.setImageResource(R.drawable.mic_on);
//                    mic.setBackgroundColor(getResources().getColor(R.color.purple_500));
//                    isMicOn = true;
//                }
//            }
//        });
    }

    private void initializeTextToVoice() {
        String welcomeText = "Hi This is VARX";
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.US);
                    tts.speak(welcomeText, TextToSpeech.QUEUE_FLUSH, null);
                    arrayList.add(welcomeText);
                    arrayList.add("1");
                    arrayList.add("2");
                    arrayList.add("3");
                    arrayList.add("4");
                    arrayList.add("5");
                    arrayList.add("6");
                    arrayList.add("7");
                    progressBar.setVisibility(View.GONE);
                    linearLayoutManager.setReverseLayout(true);
                    chatAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initializeUI() {
        send = findViewById(R.id.send);
        text = findViewById(R.id.voice_to_text);
        editText = findViewById(R.id.editText);
        progressBar = findViewById(R.id.progressBar);
        vibration = findViewById(R.id.vibration);
        vibration.setVisibility(View.GONE);
        arrayList = new ArrayList<String>();
        recyclerView = findViewById(R.id.recycleView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        linearLayoutManager.setSmoothScrollbarEnabled(true);
//        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        chatAdapter = new ChatAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(chatAdapter);
        hideActionBar();
    }

    private void initializeSpeechRecogniser() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
            }
        }
    }


    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }


    private void textToVoice() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(editText.getText().toString());
                tts.speak(editText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                editText.setText("");
                chatAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onDestroy() {
        vibration.setVisibility(View.GONE);
        super.onDestroy();
        speechRecognizer.destroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void onPause() {
        if (tts != null) {
            vibration.setVisibility(View.GONE);
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}