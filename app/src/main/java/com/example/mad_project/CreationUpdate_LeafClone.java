package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;

public class CreationUpdate_LeafClone extends AppCompatActivity {

    EditText dDate;
    Button update;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_update__leaf_clone);

        dbHandler = new DBHandler(getApplicationContext());

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerGetingType);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.customer_creation_get, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        dDate = findViewById(R.id.editTextCreDelDate);
        update = findViewById(R.id.buttonUpdate);

        Bundle bn = getIntent().getExtras();
        final String creation_id = bn.getString("Creation_ID");
        final String userName = bn.getString("User_Name");
        final String creationName = bn.getString("Creation_Name");
        final String qty = bn.getString("Quantity");
        final String price = bn.getString("Price");
        final String url = bn.getString("ImageUrl");
        final String des = bn.getString("Description");
        final String getType = bn.getString("GetType");
        final String delDate = bn.getString("DelDate");

        final double length = 11.7;
        final double width = 16.5;

        dDate.setText(delDate);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String len = String.valueOf(length);
                String wid = String.valueOf(width);
                String deliveryDate = dDate.getText().toString();
                String getType = spinner.getSelectedItem().toString();

                boolean res = dbHandler.updateCreationInfo(creation_id, userName, creationName,len, wid, url, des, qty, price, getType, deliveryDate);
                if (res){
                    Toast.makeText(CreationUpdate_LeafClone.this, "Creation Details updated", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
