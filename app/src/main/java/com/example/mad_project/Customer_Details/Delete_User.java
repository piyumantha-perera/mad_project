package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Check;
import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

public class Delete_User extends AppCompatActivity {

    DBHandler dbHandler;
    Button delete;

    ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__user);

        dbHandler = new DBHandler(getApplicationContext());
        delete = findViewById(R.id.buttonCreationDelete);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");

        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delete_User.this, Customer_Choose.class);
                intent.putExtra("Name",username);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean delete = dbHandler.deleteUserInfo(username);
                if (delete){
                    Toast.makeText(Delete_User.this, "User Account Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Delete_User.this, Check.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Delete_User.this, "User Account not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
