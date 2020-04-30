package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.Employee_Details.Employee_SalaryUpdate;
import com.example.mad_project.Employee_Details.Employee_Search;

import java.util.List;

public class SearchWorks extends AppCompatActivity {

    Button search;
    EditText eNIC;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_works);

        dbHandler = new DBHandler(getApplicationContext());

        search = findViewById(R.id.btnSearchview);
        eNIC = findViewById(R.id.editTextsearchNIC);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nic = eNIC.getText().toString();
                String name = null;
                String description = null;
                String location = null;
                String date = null;

                Cursor res = dbHandler.readWorkDetails();
                while (res.moveToNext()){
                    String empNic = res.getString(1);
                    if (empNic.equals(nic)){

                        name = res.getString(2);
                        description = res.getString(3);
                        location = res.getString(4);
                        date = res.getString(5);

                    }

                }

                Intent intent = new Intent(SearchWorks.this,ViewWorks.class);
                intent.putExtra("NIC", nic);
                intent.putExtra("Name", name);
                intent.putExtra("Description", description);
                intent.putExtra("Location", location);
                intent.putExtra("Date", date);
                startActivity(intent);

            }
        });


    }
}
