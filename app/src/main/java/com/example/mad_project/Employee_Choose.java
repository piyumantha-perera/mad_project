package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_project.Employee_Details.Employee_SalaryUpdate;

public class Employee_Choose extends AppCompatActivity {

    Button monthlySal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__choose);

        monthlySal=findViewById(R.id.buttonMonthlySal);

        monthlySal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Employee_Choose.this, Employee_SalaryUpdate.class);
                startActivity(intent);

            }
        });
    }
}
