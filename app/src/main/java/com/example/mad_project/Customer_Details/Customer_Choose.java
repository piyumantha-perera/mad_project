package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mad_project.Check;
import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

public class Customer_Choose extends AppCompatActivity {

    ImageView banner, leaflet, nameboard, lightboard;
    ImageView profile;
    TextView logout;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__choose);

        dbHandler = new DBHandler(getApplicationContext());

        banner = findViewById(R.id.imageButtonCusChoBanner);
        leaflet = findViewById(R.id.imageButtonCusChoLeaflet);
        nameboard = findViewById(R.id.imageButtonNameBoard);
        lightboard = findViewById(R.id.imageButtonLightBoard);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");

        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String creation_type = "Banner";
                Intent intent = new Intent(Customer_Choose.this, Banner.class);
                intent.putExtra("Name",username);
                intent.putExtra("creation_type", creation_type);
                startActivity(intent);
            }
        });

        leaflet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String creation_type = "Leaflet";
                Intent intent = new Intent(Customer_Choose.this, Leaflet.class);
                intent.putExtra("Name",username);
                intent.putExtra("creation_type", creation_type);
                startActivity(intent);
            }
        });

        nameboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String creation_type = "Name Board";
                Intent intent = new Intent(Customer_Choose.this, NameBoard.class);
                intent.putExtra("Name",username);
                intent.putExtra("creation_type", creation_type);
                startActivity(intent);
            }
        });

        lightboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String creation_type = "Light Board";
                Intent intent = new Intent(Customer_Choose.this, LightBoard.class);
                intent.putExtra("Name",username);
                intent.putExtra("creation_type", creation_type);
                startActivity(intent);
            }
        });

        profile = findViewById(R.id.btn_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = null;
                String email = null;
                String contact = null;
                String address = null;
                String pwd = null;
                String cnf_pwd = null;

                Cursor cursor = dbHandler.readUserDetails();
                while (cursor.moveToNext()){
                    name = cursor.getString(1);
                    if (name.equals(username)){
                        contact = cursor.getString(2);
                        email = cursor.getString(3);
                        address = cursor.getString(4);
                        pwd = cursor.getString(5);
                        cnf_pwd = cursor.getString(6);
                    }
                }

                Intent intent = new Intent(Customer_Choose.this,Customer_Profile.class);
                intent.putExtra("Name",username);
                intent.putExtra("Email",email);
                intent.putExtra("ContactNo",contact);
                intent.putExtra("Address",address);
                intent.putExtra("Password",pwd);
                intent.putExtra("CnfPassword",cnf_pwd);
                startActivity(intent);
            }
        });

        logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = null;
                String email = null;

                Cursor cursor = dbHandler.readUserDetails();
                while (cursor.moveToNext()){
                    name = cursor.getString(1);
                    if (name.equals(username)){
                        email = cursor.getString(3);
                    }
                }

                Cursor res = dbHandler.readThoughtDetails();
                String usrmail = "*";

                while (res.moveToNext()){
                    usrmail = res.getString(1);

                }
                if(usrmail.equals(email)){
                    Intent intent = new Intent(Customer_Choose.this, Check.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Customer_Choose.this, Thought.class);
                    intent.putExtra("Email", usrmail);
                    intent.putExtra("Name", username);
                    startActivity(intent);
                }


            }
        });
    }
}
