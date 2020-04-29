package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchWorks extends AppCompatActivity {

    Button viewwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_works);

        viewwork = findViewById(R.id.btnSearchview);

        viewwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchWorks.this,ViewWorks.class);
                startActivity(intent);

            }
        });
    }
}
