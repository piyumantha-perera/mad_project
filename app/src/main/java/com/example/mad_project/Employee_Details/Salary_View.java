package com.example.mad_project.Employee_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mad_project.Admin_Choose;
import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.Employee_Choose;
import com.example.mad_project.R;

import java.util.ArrayList;
import java.util.List;

public class Salary_View extends AppCompatActivity {
    ArrayList salaryList;
    ArrayAdapter adapter;
    ListView listView;

    DBHandler db;
    ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary__view);
       home = findViewById(R.id.btn_home);

        listView=findViewById(R.id.salaryList);


        db=new DBHandler(getApplicationContext());

        ListViewAdapter adapter;

        Bundle bn = getIntent().getExtras();
        final String userName = bn.getString("Name");


        List<Salary> salaryList = new ArrayList<>();

        Cursor cursor = db.readEmployeeSalary();
        while (cursor.moveToNext()){
            String name = cursor.getString(1);
            if (name.equals(userName)){
                String basic = cursor.getString(2);
                String all = cursor.getString(3);
                String ot = cursor.getString(4);
                String add = cursor.getString(5);
                String net = cursor.getString(6);
                String date = cursor.getString(7);
                salaryList.add(new Salary(name,basic,all,ot,add,net,date));

            }

        }


        adapter = new ListViewAdapter(getApplicationContext(), salaryList);
        listView.setAdapter(adapter);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Salary_View.this, Employee_Choose.class);
                intent.putExtra("Name",userName);
                startActivity(intent);
            }
        });


    }
}
