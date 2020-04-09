package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DaytoDayWork extends AppCompatActivity {

    Button office;
    Button outside;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dayto_day_work);

        office=findViewById(R.id.btnOfficeEMP);
        outside=findViewById(R.id.btnAddSalary);

        outside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( DaytoDayWork.this,OutsideEMP.class);
                startActivity(intent);
            }
        });


        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent( DaytoDayWork.this,OfficeEMP.class);
                startActivity(intent);
            }
        });
    }
}
