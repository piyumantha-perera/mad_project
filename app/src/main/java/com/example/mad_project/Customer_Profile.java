package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer_Profile extends AppCompatActivity {

    Button creation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__profile);
        creation = findViewById(R.id.buttonProfileCreation);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");

        creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Profile.this,Customer_Choose.class);
                intent.putExtra("Name",username);
                startActivity(intent);
            }
        });
    }
}
