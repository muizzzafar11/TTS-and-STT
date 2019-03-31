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

    private void speak(){
        
    }
}
