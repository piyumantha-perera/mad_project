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

import java.util.List;

public class view_employee extends AppCompatActivity {

    EditText fname, lname, email, address, contact, nic, emp_type;
    Button edit,delete,search;
    ImageView home;

    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        dbHandler = new DBHandler(getApplicationContext());
        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(view_employee.this,Admin_Choose.class);
                startActivity(intent);
            }
        });

        fname = findViewById(R.id.editfname);
        lname = findViewById(R.id.editlname);
        email= findViewById(R.id.editemail);
        address=findViewById(R.id.editaddress);
        contact = findViewById(R.id.editphone);
        nic = findViewById(R.id.editnic);
        emp_type = findViewById(R.id.edittype);

        edit = findViewById(R.id.empeditbutton);
        delete = findViewById(R.id.empdeletebutton);
        search = findViewById(R.id.empsearchbutton);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName= fname.getText().toString();

                String fName = "";
                String lName = null;
                String eEmail = null;
                String addr = null;
                String con = null;
                String eNIC = null;
                String eType = null;

                Cursor res = dbHandler.readallemployeeaddInfo();
                while (res.moveToNext()){
                    fName = res.getString(1);
                    if (fName.equals(firstName)){
                        lName = res.getString(2);
                        eEmail = res.getString(3);
                        addr = res.getString(4);
                        con = res.getString(5);
                        eNIC = res.getString(6);
                        eType = res.getString(7);

                    }

                }

                lname.setText(lName);
                email.setText(eEmail);
                address.setText(addr);
                contact.setText(con);
                nic.setText(eNIC);
                emp_type.setText(eType);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fName = fname.getText().toString();
                String lName = lname.getText().toString();
                String eEmail = email.getText().toString();
                String addr = address.getText().toString();
                String con = contact.getText().toString();
                String eNIC = nic.getText().toString();
                String eType = emp_type.getText().toString();

                Boolean result = dbHandler.updateemployeeaddInfo(fName,lName,eEmail,addr,con,eNIC,eType);

                if (result) {
                    Toast.makeText(view_employee.this, "Update successfully.", Toast.LENGTH_SHORT).show();


                    fname.setText(null);
                    lname.setText(null);
                    email.setText(null);
                    address.setText(null);
                    contact.setText(null);
                    nic.setText(null);
                    emp_type.setText(null);

                }
                }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = fname.getText().toString();

                Boolean result = dbHandler.deleteemployeeaddInfo(fName);
                if (result){
                    Toast.makeText(view_employee.this, "Delete successfully.", Toast.LENGTH_SHORT).show();

                    fname.setText(null);
                    lname.setText(null);
                    email.setText(null);
                    address.setText(null);
                    contact.setText(null);
                    nic.setText(null);
                    emp_type.setText(null);
                }
            }
        });

    }
}
