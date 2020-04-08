package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;

public class Customer_Login extends AppCompatActivity {

    EditText user, password;
    TextView register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        user = findViewById(R.id.editTextLoginUsername);
        password = findViewById(R.id.editTextLoginPassword);

        register = findViewById(R.id.textViewLoginGoReg);
        login = findViewById(R.id.buttonCusLogin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Login.this,Register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String username = user.getText().toString();
                String pwd  = password.getText().toString();

                Cursor check = dbHandler.readUserDetails();
                String email = null;
                String contact = null;
                String address = null;

                while (check.moveToNext()){
                    String name = check.getString(1);
                    String pass = check.getString(5);

                    if(name.equals(username) || pass.equals(pwd)){
                        email = check.getString(3);
                        contact = check.getString(2);
                        address = check.getString(4);
                    }
                }


                Boolean result = dbHandler.checkUser(username, pwd);

                if (username.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(Customer_Login.this, "Login error, First enter your Name and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(result == true){
                        Toast.makeText(Customer_Login.this, "Login Successfull.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Customer_Login.this,Customer_Profile.class);
                        intent.putExtra("Name",username);
                        intent.putExtra("Email",email);
                        intent.putExtra("ContactNo",contact);
                        intent.putExtra("Address",address);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Customer_Login.this, "Please enter valid username or password.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
