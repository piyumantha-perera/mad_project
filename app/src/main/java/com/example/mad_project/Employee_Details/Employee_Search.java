package com.example.mad_project.Employee_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

import java.util.ArrayList;
import java.util.List;

public class Employee_Search extends AppCompatActivity {
    private Spinner spinner;

    Button seacrh,delete;

    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__search);

        DBHandler dbHandler = new DBHandler(getApplicationContext());

        spinner = findViewById(R.id.spinnerSalSearch);
        seacrh=findViewById(R.id.btnSalaryNameSearch);
        delete=findViewById(R.id.btnSalaryDelete);

        //test = findViewById(R.id.textView67);


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

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                String name = spinner.getSelectedItem().toString();


                List user = dbHandler.readEmployeeDetails(name);

                 //String ID = (user.get(0).toString());
                 String userName=(user.get(0).toString());
                 String basicSalary=(user.get(1).toString());
                 String travellingAllowance=(user.get(2).toString());
                 String overTime=(user.get(3).toString());
                 String salaryAdvance=(user.get(4).toString());
                 String netSalary=(user.get(5).toString());


                // test.setText(userName);


                Intent i = new Intent(Employee_Search.this,Employee_SalaryUpdate.class);
                //i.putExtra("ID", ID);
                i.putExtra("UserName", userName);
                i.putExtra("BasicSalary", basicSalary);
                i.putExtra("TravellingAllowance", travellingAllowance);
                i.putExtra("OverTime", overTime);
                i.putExtra("SalaryAdvance", salaryAdvance);
                i.putExtra("NetSalary", netSalary);

                startActivity(i);



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
