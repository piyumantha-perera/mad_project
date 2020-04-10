package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Customer_Choose extends AppCompatActivity {

    ImageView banner, leaflet, nameboard, lightboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__choose);

        banner = findViewById(R.id.imageButtonCusChoBanner);
        leaflet = findViewById(R.id.imageButtonCusChoLeaflet);
        nameboard = findViewById(R.id.imageButtonNameBoard);
        lightboard = findViewById(R.id.imageButtonLightBoard);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");

        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String creation_type = "Banner";
                Intent intent = new Intent(Customer_Choose.this,Banner.class);
                intent.putExtra("Name",username);
                intent.putExtra("creation_type", creation_type);
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

        nameboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Choose.this, NameBoard.class);
                startActivity(intent);
            }
        });

        lightboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Choose.this, LightBoard.class);
                startActivity(intent);
            }
        });
    }
}
