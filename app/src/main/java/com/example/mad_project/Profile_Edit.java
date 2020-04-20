package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Customer_Details.Customer_Profile;
import com.example.mad_project.Database.DBHandler;

public class Profile_Edit extends AppCompatActivity {

    DBHandler dbHandler;

    TextView user;
    EditText mail, contact, addr, pass, cnfPass;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__edit);

        dbHandler = new DBHandler(getApplicationContext());

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

        update = findViewById(R.id.buttonUpdate);

        user.setText(username);
        mail.setText(email);
        contact.setText(contactNo);
        addr.setText(address);
        pass.setText(password);
        cnfPass.setText(cnf_pass);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = user.getText().toString();
                String contactno = contact.getText().toString();
                String userEmail = mail.getText().toString();
                String usrAddr = addr.getText().toString();
                String usrPass = pass.getText().toString();
                String usrCnfPass = cnfPass.getText().toString();

                Boolean update = dbHandler.updateUserInfo(userName,contactno, userEmail, usrAddr, usrPass, usrCnfPass);

                if (update){
                    Toast.makeText(Profile_Edit.this, "User Details updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Profile_Edit.this, Customer_Profile.class);
                    intent.putExtra("Name",userName);
                    intent.putExtra("Email",userEmail);
                    intent.putExtra("ContactNo",contactno);
                    intent.putExtra("Address",usrAddr);
                    intent.putExtra("Password",usrPass);
                    intent.putExtra("CnfPassword",usrCnfPass);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Profile_Edit.this, "Creation Details not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
