package com.example.a4thyearproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    Button editBtn;
    Button homeBtn;
    TextView mFname,mSname,mUni,mBio, mAge, mGender;
    ImageView imgV;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        editBtn = findViewById(R.id.editBtn);
        homeBtn = findViewById(R.id.homeButton);

        mFname = findViewById(R.id.fName);
        mSname = findViewById(R.id.lName);
        mUni = findViewById(R.id.Uni);
        mBio = findViewById(R.id.Bio);
        mAge = findViewById(R.id.age);
        mGender = findViewById(R.id.gender);


        SharedPreferences sp = getApplicationContext().getSharedPreferences("myPref", Context.MODE_PRIVATE);
        String fName = sp.getString("fName","");
        String sName = sp.getString("sName","");
        String bio = sp.getString("bio","");
        String uni = sp.getString("uni","");
        String age = sp.getString("age","");
        String gender = sp.getString("gender","");



        mFname.setText(fName);
        mSname.setText(sName);
        mUni.setText(uni);
        mBio.setText(bio);
        mAge.setText(age);
        mGender.setText(gender);





        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);

                startActivity(intent);
            }
        });



    }


}