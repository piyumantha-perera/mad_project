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

    String PWD_PATTERN = "((?=.*[a-z])(?=.*[@#$%!]).{6,40})";
    String MobilePattern = "[0-9]{10}";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


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

                if (username.isEmpty() || password.isEmpty() || userEmail.isEmpty() || cntNo.isEmpty()) {
                    user.setError("please enter the user name");
                    contact.setError("please enter the user contact");
                    email.setError("please enter the email");
                    pwd.setError("please enter the password");

                }
                else{
                    if(cntNo.matches(MobilePattern)){
                        if(userEmail.matches(emailPattern)) {
                            if(password.matches(PWD_PATTERN)){
                                if(password.equals(cnf_password)){
                                    if(name.equals("*") || !(name.equals(username))) {

                                        long newId = dbHandler.addUserDetails(username, cntNo, userEmail, addr, password, cnf_password);
                                        Toast.makeText(Register.this, "User Details added. User ID: "+newId, Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(Register.this, Customer_Login.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(Register.this, "Username has been use before, Enter another username.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(Register.this, "Please Enter matching Confirm Password.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(Register.this, "please enter the Strong password.", Toast.LENGTH_SHORT).show();
                                pwd.setError("please enter the Strong password");

                            }
                        }
                        else{
                            Toast.makeText(Register.this, "please enter the valid user Email.", Toast.LENGTH_SHORT).show();
                            email.setError("please enter the valid user Email.");
                        }

                    }
                    else {
                        Toast.makeText(Register.this, "please enter the valid user contact.", Toast.LENGTH_SHORT).show();
                        contact.setError("please enter the valid user contact.");
                    }

                }


                }



        });

    }
}
