package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.Employee_Details.Employee_Salary;

public class add_employee extends AppCompatActivity {

    String MobilePattern = "[0-9]{10}";
    String nicPattern = "[0-9]{9}[vV]";

    EditText fname, lname, email, address, contact, nic, emp_type;
    Button add, update;
    ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        contact = findViewById(R.id.phone);
        nic = findViewById(R.id.nic);
        emp_type = findViewById(R.id.type);

        add = findViewById(R.id.addempbutton);
        update = findViewById(R.id.updateempbutton);

        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(add_employee.this,Admin_Choose.class);
                startActivity(intent);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), view_employee.class);
                startActivity(intent);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String empfname, emplname, empEmail, addr, cntNo, emp_nic, employee_type;
                empfname = fname.getText().toString();
                emplname = lname.getText().toString();
                empEmail = email.getText().toString();
                addr = address.getText().toString();
                cntNo = contact.getText().toString();
                emp_nic = nic.getText().toString();
                employee_type = emp_type.getText().toString();

                if (empfname.isEmpty() || emp_nic.isEmpty() || cntNo.isEmpty()) {
                    fname.setError(" enter the name");
                    nic.setError(" enter the NIC");
                    contact.setError("enter the contact no:");

                } else {
                    if(cntNo.matches(MobilePattern)){
                        if (emp_nic.matches(nicPattern)){
                            long newId = dbHandler.addEmployeeAddDetails(empfname, emplname, empEmail, addr, cntNo, emp_nic, employee_type);
                            Toast.makeText(add_employee.this, "Added successfully.employeeID :" + newId, Toast.LENGTH_SHORT).show();

                            fname.setText(null);
                            lname.setText(null);
                            email.setText(null);
                            address.setText(null);
                            contact.setText(null);
                            nic.setText(null);
                            emp_type.setText(null);
                        }else{
                            Toast.makeText(add_employee.this, "Please enter the valid NIC", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(add_employee.this, "Please enter the valid phone number", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });
    }
}


