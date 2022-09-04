package com.example.a4thyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    ImageView profilePhoto;
    ImageView rent;
    ImageView maps;
    ImageView elderly;
    ImageView digs, roomShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        profilePhoto = findViewById(R.id.proPic);
        rent = findViewById(R.id.rentPhoto);
        maps = findViewById(R.id.Map);
        elderly = findViewById(R.id.elder);
        digs = findViewById(R.id.digsDash);
        roomShare = findViewById(R.id.room1);



        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Rent.class);
                startActivity(intent);
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        elderly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ElderlyActivity.class);
                startActivity(intent);
            }
        });

        digs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Digs.class);
                startActivity(intent);
            }
        });

        roomShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, RoomShare.class);
                startActivity(intent);
            }
        });

    }


}