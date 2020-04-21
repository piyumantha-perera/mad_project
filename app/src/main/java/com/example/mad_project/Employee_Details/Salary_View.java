package com.example.mad_project.Employee_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

import java.util.ArrayList;

public class Salary_View extends AppCompatActivity {
    ArrayList salaryList;
    ArrayAdapter adapter;
    ListView listView;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary__view);





        listView=findViewById(R.id.salaryList);

        db=new DBHandler(getApplicationContext());

        salaryList = db.readSalaryDetails();

        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,salaryList);
        listView.setAdapter(adapter);

    }
}
