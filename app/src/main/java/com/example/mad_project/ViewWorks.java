package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;

public class ViewWorks extends AppCompatActivity {
    EditText nic,employeename,work_description,location,date;
    Button addnewwork, delete,update;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_works);
        dbHandler = new DBHandler(getApplicationContext());

        nic = findViewById(R.id.editTextNICW);
        employeename = findViewById(R.id.editTextEMPnameW);
        work_description= findViewById(R.id.editTextWorkDes);
        location=findViewById(R.id.editTextlocationW);
        date = findViewById(R.id.editTextdateW);

        addnewwork = findViewById(R.id.btnSearchview);
        delete = findViewById(R.id.btnDeleteW);
        update = findViewById(R.id.btnUpdtW);

        addnewwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewWorks.this,OutsideEMP.class);
                startActivity(intent);

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eNIC = nic.getText().toString();
                String eName = employeename.getText().toString();
                String workDesc = work_description.getText().toString();
                String eloct = location.getText().toString();
                String wdate = date.getText().toString();


                Boolean result = dbHandler.UpdateEmpWorks(eNIC,eName,workDesc,eloct,wdate);

                if (result) {
                    Toast.makeText(ViewWorks.this, "Update successfully.", Toast.LENGTH_SHORT).show();


                    nic.setText(null);
                    employeename.setText(null);
                    work_description.setText(null);
                    location.setText(null);
                    date.setText(null);


                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eNIC = nic.getText().toString();

                Boolean result = dbHandler.DeleteEmpWorks(eNIC);
                if (result){
                    Toast.makeText(ViewWorks.this, "Delete successfully.", Toast.LENGTH_SHORT).show();

                    nic.setText(null);
                    employeename.setText(null);
                    work_description.setText(null);
                    location.setText(null);
                    date.setText(null);

                }
            }
        });
    }
}
