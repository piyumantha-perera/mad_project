package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Employee_Login extends AppCompatActivity {

    Button dayWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__login);

        dayWork=findViewById(R.id.buttonEmployeeLogin);

        dayWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Employee_Login.this,EmployeeWorks.class);
                startActivity(intent);
            }
        });
    }
}
