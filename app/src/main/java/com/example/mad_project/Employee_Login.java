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

public class Employee_Login extends AppCompatActivity {

    Button login;
    EditText name,pss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__login);

        login=findViewById(R.id.buttonEmployeeLogin);
        name=findViewById(R.id.editTextEmpLogUsrNme);
        pss=findViewById(R.id.editTextEmpLgnPass);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String username = name.getText().toString();
                String pwd  = pss.getText().toString();

                Boolean result = dbHandler.checkEmployee(username, pwd);



                if (username.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(Employee_Login.this, "Login error, First enter your Name and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(result == true){
                        Toast.makeText(Employee_Login.this, "Login Successfull.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Employee_Login.this, Employee_Choose.class);
                        intent.putExtra("Name",username);

                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(Employee_Login.this, "Please enter valid username or password.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }
}
