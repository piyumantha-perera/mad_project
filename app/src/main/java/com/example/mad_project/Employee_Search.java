package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class Employee_Search extends AppCompatActivity {
    private Spinner spinner;

    EditText userName,BasicSalary,TravellingAllowance,OverTime,SalaryAdvance,NetSalary;
    Button seacrh,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__search);

        DBHandler dbHandler = new DBHandler(getApplicationContext());

        spinner = findViewById(R.id.spinnerSalSearch);
        seacrh=findViewById(R.id.btnSalaryNameSearch);
        delete=findViewById(R.id.btnSalaryDelete);

//        BasicSalary = findViewById(R.id.editTextSalDetBasSal);
//        TravellingAllowance = findViewById(R.id.editTextSalDetTravAll);
//        OverTime = findViewById(R.id.editTextSalDetOt);
//        SalaryAdvance = findViewById(R.id.editTextSalDetSalAdv);
//        NetSalary = findViewById(R.id.editTextSalDetNetSal);



        List<String> names = new ArrayList<>();
        names.add(0, "Choose The Employee Names");

        Cursor cursor = dbHandler.readEmpspinNme();
        while (cursor.moveToNext()){
            String name = cursor.getString(1);
            names.add(name);
        }

        ArrayAdapter<String> adapter;
        adapter = new  ArrayAdapter(Employee_Search.this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        seacrh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // DBHandler dbHandler = new DBHandler(getApplicationContext());



                /*dbHandler.readEmployeeDetails();
                Toast.makeText(Employee_Search.this, "Search success.", Toast.LENGTH_SHORT).show();*/



            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String name = spinner.getSelectedItem().toString();

                dbHandler.deleteEmployeeInfo(name);
                Toast.makeText(Employee_Search.this, " Deleted success .", Toast.LENGTH_SHORT).show();

            }
        });



    }
}
