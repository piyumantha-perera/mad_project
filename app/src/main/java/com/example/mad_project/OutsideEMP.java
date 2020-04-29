package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;

public class OutsideEMP extends AppCompatActivity {

    Button searchwork, add;
    EditText nic,employeename,work_description,location,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outside_emp);

        nic = findViewById(R.id.empIDW);
        employeename = findViewById(R.id.empNameW);
        work_description= findViewById(R.id.workDescW);
        location=findViewById(R.id.locationW);
        date = findViewById(R.id.dateW);

        searchwork = findViewById(R.id.btnSearchW);
        add=findViewById(R.id.btnAddW);


        searchwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutsideEMP.this,SearchWorks.class);
                startActivity(intent);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String eNIC,eName,eWorkDes,elocation,eDate;
                eNIC = nic.getText().toString();
                eName=employeename.getText().toString();
                eWorkDes=work_description.getText().toString();
                elocation=location.getText().toString();
                eDate = date.getText().toString();


                long newId =  dbHandler.ADDEmployeeWorksDetails(eNIC, eName, eWorkDes, elocation, eDate);
                Toast.makeText(OutsideEMP.this, "Added successfully :" +newId, Toast.LENGTH_SHORT).show();

                //Intent intent=new Intent(getApplicationContext(),view_employee.class);
                //startActivity(intent);

                nic.setText(null);
                employeename.setText(null);
                work_description.setText(null);
                location.setText(null);
                date.setText(null);

            }



        });
    }
}
