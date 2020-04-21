package com.example.mad_project.Employee_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

import java.util.ArrayList;
import java.util.List;

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
                salaryList.add(new Salary(name,basic,all,ot,add,net));

            }

        }


        adapter = new ListViewAdapter(getApplicationContext(), salaryList);
        listView.setAdapter(adapter);

    }
}
