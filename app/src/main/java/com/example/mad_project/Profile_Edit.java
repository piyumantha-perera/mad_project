package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Profile_Edit extends AppCompatActivity {

    TextView user;
    EditText mail, contact, addr, pass, cnfPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__edit);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");
        final String email = bn.getString("Email");
        final String contactNo = bn.getString("ContactNo");
        final String address = bn.getString("Address");
        final String password = bn.getString("Password");
        final String cnf_pass = bn.getString("CnfPassword");

        user = findViewById(R.id.textViewUsername);
        mail = findViewById(R.id.editTextRegEmail);
        contact = findViewById(R.id.editTextRegContact);
        addr = findViewById(R.id.editTextRegAddress);
        pass = findViewById(R.id.editTextRegisterPassword);
        cnfPass = findViewById(R.id.editTextRegCnfPwd);

        user.setText(username);
        mail.setText(email);
        contact.setText(contactNo);
        addr.setText(address);
        pass.setText(password);
        cnfPass.setText(cnf_pass);

    }
}
