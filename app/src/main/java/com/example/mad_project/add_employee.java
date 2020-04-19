package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;

public class add_employee extends AppCompatActivity {

    EditText fname, lname, email, address, contact, nic, emp_type;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email= findViewById(R.id.email);
        address=findViewById(R.id.address);
        contact = findViewById(R.id.phone);
        nic = findViewById(R.id.nic);
        emp_type = findViewById(R.id.type);

        add = findViewById(R.id.addempbutton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String empfname,emplname,empEmail,addr,cntNo,emp_nic,employee_type;
                empfname = fname.getText().toString();
                emplname=lname.getText().toString();
                empEmail=email.getText().toString();
                addr=address.getText().toString();
                cntNo = contact.getText().toString();
                emp_nic = nic.getText().toString();
                employee_type = emp_type.getText().toString();

                long newId =  dbHandler.addEmployeeAddDetails(empfname, emplname, empEmail, addr, cntNo, emp_nic, employee_type);
                Toast.makeText(add_employee.this, "Added successfully", Toast.LENGTH_SHORT).show();


            }



        });
    }
}
