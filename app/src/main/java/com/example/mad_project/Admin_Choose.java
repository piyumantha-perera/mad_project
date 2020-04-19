package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Choose extends AppCompatActivity {

    Button salary;
    Button work;
    Button empadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__choose);

        salary=findViewById(R.id.buttonAdChAddPayments);
        work = findViewById(R.id.buttonAdChAddWorks);
        empadd=findViewById(R.id.buttonAdChAddEmp);

        empadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( Admin_Choose.this,Employee_Add.class);
                startActivity(intent);

            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( Admin_Choose.this,DaytoDayWork.class);
                startActivity(intent);

            }
        });

        salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( Admin_Choose.this,Employee_Salary.class);
                startActivity(intent);
            }
        });
    }
}
