package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;

public class Employee_Search extends AppCompatActivity {

    EditText userName,BasicSalary,TravellingAllowance,OverTime,SalaryAdvance,NetSalary;
    Button seacrh,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__search);

        userName = findViewById(R.id.editTextEmpNameSearch);
//        BasicSalary = findViewById(R.id.editTextSalDetBasSal);
//        TravellingAllowance = findViewById(R.id.editTextSalDetTravAll);
//        OverTime = findViewById(R.id.editTextSalDetOt);
//        SalaryAdvance = findViewById(R.id.editTextSalDetSalAdv);
//        NetSalary = findViewById(R.id.editTextSalDetNetSal);
        seacrh=findViewById(R.id.btnSalaryNameSearch);
        delete=findViewById(R.id.btnSalaryDelete);

        seacrh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                dbHandler.readEmployeeDetails(userName.getText().toString());
                Toast.makeText(Employee_Search.this, "Search success.", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                dbHandler.deleteEmployeeInfo(userName.getText().toString());
                Toast.makeText(Employee_Search.this, " Deleted success .", Toast.LENGTH_SHORT).show();

            }
        });



    }
}
