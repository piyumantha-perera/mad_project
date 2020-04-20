package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mad_project.Database.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class CreationUpdate extends AppCompatActivity {

    Spinner spinner;
    DBHandler dbHandler;

    ImageView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_update);

        dbHandler = new DBHandler(getApplicationContext());


        search = findViewById(R.id.imageButtonSearch);

        spinner = findViewById(R.id.spinner);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");

        final List<String> names = new ArrayList<>();
        names.add(0, "Select ID");

        Cursor cursor = dbHandler.readCreationDetails();

        String creationId = null , creationType = null;

        while (cursor.moveToNext()){
        String user = cursor.getString(1);
            if (user.equals(username)){
                creationId = cursor.getString(0);
                names.add(creationId);
            }
        }

        ArrayAdapter<String> adapter;
        adapter = new  ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void search(View view){
        Fragment fragment;
        String creationName = null;

        String creationID = spinner.getSelectedItem().toString();

        Cursor cursor = dbHandler.readCreationDetails();
        while (cursor.moveToNext()){
            String creId = cursor.getString(0);
            if (creId.equals(creationID)){
                creationName = cursor.getString(2);
            }

        }

        String name = null, nameClone = null;
        if (creationName.equals("Leaflet")){
            if (view == findViewById(R.id.imageButtonSearch)){
                fragment = new FragmentLeaflet();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle bn = new Bundle();
                bn.putString("Creation ID", creationID);
                fragment.setArguments(bn);
                ft.replace(R.id.fgmntDefault,fragment);
                ft.commit();
            }
        }
        else {
            if (view == findViewById(R.id.imageButtonSearch)){
                fragment = new FragmentBoard();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle bn = new Bundle();
                bn.putString("Creation ID", creationID);
                fragment.setArguments(bn);
                ft.replace(R.id.fgmntDefault,fragment);
                ft.commit();
            }
        }

    }
}
