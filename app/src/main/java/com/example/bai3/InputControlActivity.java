package com.example.bai3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class InputControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_control);
        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.constraintLayout2);

        Switch wifiSwitch = (Switch) findViewById(R.id.wifiSwitch);
        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "Wifi is being on", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Wifi is being off", Toast.LENGTH_LONG).show();
                }
            }
        });

        CheckBox backgroundCheckBox = (CheckBox) findViewById(R.id.backgroundCheckBox);
        backgroundCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    bg.setBackgroundResource(R.drawable.background1);
                }else{
                    bg.setBackgroundResource(R.drawable.background);
                }
            }
        });

        RadioGroup radioGroupBackground = (RadioGroup) findViewById(R.id.radioGroupBackground);
        radioGroupBackground.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int isCheckedId) {
                if(isCheckedId == R.id.radioButtonBackground2){
                    bg.setBackgroundResource(R.drawable.background2);
                } else {
                    bg.setBackgroundResource(R.drawable.background3);
                }
            }
        });

        ImageButton openImageButton1 = (ImageButton) findViewById(R.id.openImageButton1);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressHorizontal);
        openImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*int current = progressBar.getProgress();
                progressBar.setProgress(current + 10);
                if(current == 100){
                    progressBar.setProgress(0);
                }*/
                CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int current = progressBar.getProgress();
                        if (current>= progressBar.getMax()){
                            current=0;
                        }
                        progressBar.setProgress(current + 10);
                    }
                    @Override
                    public void onFinish() {
                        Toast.makeText(getApplicationContext(),"Hết giờ",Toast.LENGTH_LONG).show();
                    }
                };
                countDownTimer.start();
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("AAA", "Value:" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("AAA", "Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("AAA", "Stop");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }
}