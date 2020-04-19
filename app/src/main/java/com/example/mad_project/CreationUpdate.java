package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mad_project.Database.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class CreationUpdate extends AppCompatActivity {

    Spinner spinner;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_update);

        dbHandler = new DBHandler(getApplicationContext());

        spinner = findViewById(R.id.spinner);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");

        List<String> names = new ArrayList<>();
        names.add(0, "Select ID");

        Cursor cursor = dbHandler.readCreationDetails();



        while (cursor.moveToNext()){
        String user = cursor.getString(1);
            if (user.equals(username)){
                String creationId = cursor.getString(0);
                String creationType = cursor.getString(2);
                names.add("ID: "+creationId+"  "+"Creation Type: "+creationType);
            }

        }

        ArrayAdapter<String> adapter;
        adapter = new  ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}
