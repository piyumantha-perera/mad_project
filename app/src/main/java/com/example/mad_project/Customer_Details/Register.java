package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

public class Register extends AppCompatActivity {

    Button register;
    EditText user, contact, email, address, pwd, cnf_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = findViewById(R.id.editTextRegisterName);
        contact = findViewById(R.id.editTextRegContact);
        email = findViewById(R.id.editTextRegEmail);
        address = findViewById(R.id.editTextRegAddress);
        pwd = findViewById(R.id.editTextRegisterPassword);
        cnf_pwd = findViewById(R.id.editTextRegCnfPwd);

        register = findViewById(R.id.buttonUpdate);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String username, cntNo, userEmail, addr, password, cnf_password;
                username = user.getText().toString();
                cntNo = contact.getText().toString();
                userEmail = email.getText().toString();
                addr = address.getText().toString();
                password = pwd.getText().toString();
                cnf_password = cnf_pwd.getText().toString();

                Cursor check = dbHandler.readUserDetails();

                String name = "*";
                while (check.moveToNext()){
                    name = check.getString(1);
                }
                if(name.equals("*") || !(name.equals(username))) {

                        if(password.equals(cnf_password)){
                            long newId = dbHandler.addUserDetails(username, cntNo, userEmail, addr, password, cnf_password);
                            Toast.makeText(Register.this, "User Details added. User ID: "+newId, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Register.this, Customer_Login.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Register.this, "Please Enter matching Confirm Password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                    Toast.makeText(Register.this, "Username has been use before, Enter another username.", Toast.LENGTH_SHORT).show();
                }

                }



        });

    }
}
