package com.example.bai3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupMenu;
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

        //Swtich - Toggle
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

        //Checkbox
        CheckBox backgroundCheckBox = (CheckBox) findViewById(R.id.backgroundCheckBox);
        backgroundCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    bg.setBackgroundResource(R.drawable.pink_gradient_background);
                }else{
                    bg.setBackgroundResource(R.drawable.purple_gradient_background);
                }
            }
        });

        //Radio button
        RadioGroup radioGroupBackground = (RadioGroup) findViewById(R.id.radioGroupBackground);
        radioGroupBackground.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int isCheckedId) {
                if(isCheckedId == R.id.radioButtonBackground2){
                    bg.setBackgroundResource(R.drawable.blue_gradient_background);
                } else {
                    bg.setBackgroundResource(R.drawable.red_gradient_background);
                }
            }
        });

        //Image button
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
                CountDownTimer countDownTimer = new CountDownTimer(6000,1000) {
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
                        Toast.makeText(getApplicationContext(),"Time out",Toast.LENGTH_LONG).show();
                    }
                };
                countDownTimer.start();
            }
        });

        //Seek bar
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

        //Open popup menu
        Button btnSetting = (Button) findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopupMenu();
            }
        });

        //Register context menu
        Button btnSetting2 = (Button) findViewById(R.id.btnSetting2);
        registerForContextMenu(btnSetting2);

        //Open alert dialog
        Button btnDialog = (Button) findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(bg);
            }
        });

    }

    //Call menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Menu event
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuShare:
                Toast.makeText(getApplicationContext(),"Shared your choice", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuSetting:
                Toast.makeText(getApplicationContext(),"Open Settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuLogout:
                Toast.makeText(getApplicationContext(),"You are logged out", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Popup menu
    private void ShowPopupMenu() {
        Button btnSetting = (Button) findViewById(R.id.btnSetting);
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), btnSetting);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menuAccount:
                        Toast.makeText(InputControlActivity.this, "You choose Account", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuPrivacy:
                        Toast.makeText(InputControlActivity.this, "You choose Privacy", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        popupMenu.show();
    }

    //Context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        menu.setHeaderTitle("Context Menu");
        menu.setHeaderIcon(R.mipmap.ic_launcher_round);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAccount:
                Toast.makeText(InputControlActivity.this, "You choose Account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuPrivacy:
                Toast.makeText(InputControlActivity.this, "You choose Privacy", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    //Alert dialog
    public void showAlertDialog(ConstraintLayout constraintLayout){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Notice");
        alert.setMessage("You just opened alert dialog. Do you want to change another background?");
        alert.setPositiveButton("Okay, thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                constraintLayout.setBackgroundResource(R.drawable.blue_gradient_background);
            }
        });
        alert.setNegativeButton("Don't need", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Close alert dialog
            }
        });
        alert.show();
    }
}