package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;

import java.util.List;

public class view_employee extends AppCompatActivity {

    EditText fname, lname, email, address, contact, nic, emp_type;
    Button edit,delete,search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        fname = findViewById(R.id.editfname);
        lname = findViewById(R.id.editlname);
        email= findViewById(R.id.email);
        address=findViewById(R.id.editaddress);
        contact = findViewById(R.id.editphone);
        nic = findViewById(R.id.editnic);
        emp_type = findViewById(R.id.edittype);

        edit = findViewById(R.id.empeditbutton);
        delete = findViewById(R.id.empdeletebutton);
        search = findViewById(R.id.empsearchbutton);


    }
}
