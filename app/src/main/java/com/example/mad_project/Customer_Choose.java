package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Customer_Choose extends AppCompatActivity {

    ImageView banner, leaflet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__choose);

        banner = findViewById(R.id.imageButtonCusChoBanner);
        leaflet = findViewById(R.id.imageButtonCusChoLeaflet);

        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Choose.this,Banner.class);
                startActivity(intent);
            }
        });

        leaflet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Choose.this, Leaflet.class);
                startActivity(intent);
            }
        });
    }
}
