package com.example.a4thyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class VIewPropertiesLandlord extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_properties_landlord);

        btn1 = findViewById(R.id.btnView1);
        btn2 = findViewById(R.id.btnView2);
        btn3 = findViewById(R.id.btnView3);
        btn4 = findViewById(R.id.btnView4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VIewPropertiesLandlord.this, Rent.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VIewPropertiesLandlord.this, Digs.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VIewPropertiesLandlord.this, ElderlyActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VIewPropertiesLandlord.this, ElderlyActivity.class);
                startActivity(intent);
            }
        });
    }
}