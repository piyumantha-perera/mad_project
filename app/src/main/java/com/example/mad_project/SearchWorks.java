package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.Employee_Details.Employee_SalaryUpdate;
import com.example.mad_project.Employee_Details.Employee_Search;

import java.util.List;

public class SearchWorks extends AppCompatActivity {

    Button updtework, search;
    EditText eNIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_works);

        updtework = findViewById(R.id.btnSearchview);
        search=findViewById(R.id.btnSearchview);

        eNIC = findViewById(R.id.editTextsearchNIC);

        updtework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchWorks.this,ViewWorks.class);
                startActivity(intent);

            }
        });


    }
}
