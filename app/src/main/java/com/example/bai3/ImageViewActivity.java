package com.example.bai3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.constraintLayout1);
        imageView.setImageResource(R.drawable.law_photo);

        //Random background
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.blue_gradient_background);
        arrayList.add(R.drawable.purple_gradient_background);

        Random random = new Random();
        int vitri = random.nextInt(arrayList.size());
        bg.setBackgroundResource(arrayList.get(vitri));

        //Image Button
        ImageButton openImageButton = (ImageButton) findViewById(R.id.openImageButton);
        openImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.avatar);
            }
        });

    }

}