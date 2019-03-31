package com.example.tts;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech TTS;
    private Button button;
    private SeekBar pitch, speed;
    private EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button_speak);
        TTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //if the
                if(status == TextToSpeech.SUCCESS){
                    //setting the locale language to english
                    int result = TTS.setLanguage(Locale.ENGLISH);
                    //if the language is not supported
                    //or if there os nothing in the data
                    //then show the log (prompt)
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "language is not supported");
                    }
                    //if no errors
                    else{
                        button.setEnabled(true);
                    }
                }
                //giving a prompt for error
                else{
                    Log.e("TTS", "Initialization Failed");
                }
            }
        });

        text = findViewById(R.id.edit_text);
        pitch = findViewById(R.id.seek_bar_pitch);
        speed = findViewById(R.id.seek_bar_speed);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            speak();
            }
        });

    }
    //method for converting text to speech
    private void speak(){
    //declaring a string for the conversion of text to speech
        String text2 = text.getText().toString();
        float pitch2 = (float) pitch.getProgress() / 50 ;
        //so that the pitch is not at zero
        if ((pitch2 < 0.1)){
            pitch2 = 0.1f;
        }
        else if (pitch2 > 100) {
            pitch2 = 100f;
        }
        float speed2 = (float) speed.getProgress() / 50 ;
        if (speed2 < 0.1){
            speed2 = 0.1f;
        }

        TTS.setPitch(pitch2);
        TTS.setSpeechRate(speed2);
         TTS.speak(text2,TextToSpeech.QUEUE_FLUSH,null);

    }
//method to stop the text to speech
    @Override
    protected void onDestroy() {
        if(TTS != null){
            TTS.stop();
            TTS.shutdown();

        }
        super.onDestroy();
    }
}
