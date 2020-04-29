package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewWorks extends AppCompatActivity {

    Button addnewwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_works);

        addnewwork = findViewById(R.id.btnSearchview);

        addnewwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewWorks.this,OutsideEMP.class);
                startActivity(intent);

            }
        });
    }
}
