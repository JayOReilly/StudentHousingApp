package com.example.a4thyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LandActivity extends AppCompatActivity {

    ImageView btn1,btn2,btn3,btn4,btn5,btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);

        btn1 = findViewById(R.id.image1);
        btn2 = findViewById(R.id.image2);
        btn3 = findViewById(R.id.image3);
        btn4 = findViewById(R.id.image4);
        btn5 = findViewById(R.id.image5);
        btn6 = findViewById(R.id.image6);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandActivity.this, LandlordActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandActivity.this, DigsActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandActivity.this, ElderlyLandlord.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandActivity.this, RoomShareLandlord.class);
                startActivity(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });


        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandActivity.this, VIewPropertiesLandlord.class);
                startActivity(intent);
            }
        });



    }
}