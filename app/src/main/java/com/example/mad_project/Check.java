package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mad_project.Customer_Details.Customer_Login;

public class Check extends AppCompatActivity {

    ImageView cutomer;
    ImageView admin;
    ImageView employe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        cutomer = findViewById(R.id.imageButtonCheckCustomer);
        admin = findViewById(R.id.imageButtonCheckAdmin);
        employe=findViewById(R.id.imageButtonCheckEmployee);

        cutomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check.this, Customer_Login.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check.this, Admin_Login.class);
                startActivity(intent);
            }
        });

        employe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Check.this,Employee_Login.class);
                startActivity(intent);

            }
        });



    }
}
