package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_project.Employee_Details.Employee_Salary;

public class Admin_Choose extends AppCompatActivity {

    Button salary;
    Button empAdd;
    Button work;
    Button empadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__choose);

        salary = findViewById(R.id.buttonAdChAddPayments);
        //emp = findViewById(R.id.buttonAdChAddEmp);
        work = findViewById(R.id.buttonAdChAddWorks);
        empAdd=findViewById(R.id.buttonAdChAddEmp);
        empadd=findViewById(R.id.buttonAdChAddEmp);

        empadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( Admin_Choose.this,add_employee.class);
                startActivity(intent);

            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( Admin_Choose.this,OutsideEMP.class);
                startActivity(intent);

            }
        });

        salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Admin_Choose.this, Employee_Salary.class);
                startActivity(intent);
            }
        });

        empAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Admin_Choose.this,add_employee.class);
                startActivity(intent);
            }
        });

    }
}
