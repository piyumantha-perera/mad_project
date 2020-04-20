package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mad_project.Home.Check;
import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

public class Customer_Profile extends AppCompatActivity {

    Button creation, edit, update;
    TextView user, usrEmail, usrContact, usrAddress, logout;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__profile);

        creation = findViewById(R.id.buttonProfileCreation);
        edit = findViewById(R.id.buttonProEdit);
        update = findViewById(R.id.buttonUpdate);

        dbHandler = new DBHandler(getApplicationContext());

        logout = findViewById(R.id.btn_logout);

        user = findViewById(R.id.textViewCusPUsername);
        usrEmail = findViewById(R.id.textViewCusPEmail);
        usrContact = findViewById(R.id.textViewCusPContact);
        usrAddress = findViewById(R.id.textViewCusPCompany);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");
        final String email = bn.getString("Email");
        final String contactNo = bn.getString("ContactNo");
        final String address = bn.getString("Address");
        final String password = bn.getString("Password");
        final String cnf_pass = bn.getString("CnfPassword");

        user.setText(username);
        usrEmail.setText(email);
        usrContact.setText(contactNo);
        usrAddress.setText(address);

        creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Profile.this, Customer_Choose.class);
                intent.putExtra("Name",username);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEmail = usrEmail.getText().toString();

                Cursor cursor = dbHandler.readThoughtDetails();
                String usrmail = "*";

                while (cursor.moveToNext()){
                    usrmail = cursor.getString(1);
                }
                if(usrmail.equals(userEmail)){
                    Intent intent = new Intent(Customer_Profile.this, Check.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Customer_Profile.this, Thought.class);
                    intent.putExtra("Email", userEmail);
                    startActivity(intent);
                }


            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Customer_Profile.this, Profile_Edit.class);
                intent.putExtra("Name",username);
                intent.putExtra("Email",email);
                intent.putExtra("ContactNo",contactNo);
                intent.putExtra("Address",address);
                intent.putExtra("Password",password);
                intent.putExtra("CnfPassword",cnf_pass);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Profile.this, CreationUpdate.class);
                intent.putExtra("Name",username);
                startActivity(intent);
            }
        });
    }
}
