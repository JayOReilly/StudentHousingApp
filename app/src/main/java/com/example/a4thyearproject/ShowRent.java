package com.example.a4thyearproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowRent extends AppCompatActivity {

    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rent);

        t1 = findViewById(R.id.show1);

        String price = getIntent().getStringExtra("Price");

        t1.setText(price);
    }
}