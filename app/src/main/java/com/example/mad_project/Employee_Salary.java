package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;

public class Employee_Salary extends AppCompatActivity {

    ImageView home;
    EditText userName,BasicSalary,TravellingAllowance,OverTime,SalaryAdvance,NetSalary;
    Button add,viewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__salary);


        userName = findViewById(R.id.editTextSalDetName);
        BasicSalary = findViewById(R.id.editTextSalDetBasSal);
        TravellingAllowance = findViewById(R.id.editTextSalDetTravAll);
        OverTime = findViewById(R.id.editTextSalDetOt);
        SalaryAdvance = findViewById(R.id.editTextSalDetSalAdv);
        NetSalary = findViewById(R.id.editTextEmpSalDetNet);

        add = findViewById(R.id.btnAddSalary);

        viewDetails=findViewById(R.id.buttonSalViewDetails);

        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Employee_Salary.this,Check.class);
                startActivity(intent);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String username, basSal, travAll, ot, salAdv, netSal;

                username = userName.getText().toString();
                basSal = BasicSalary.getText().toString();
                travAll = TravellingAllowance.getText().toString();
                ot = OverTime.getText().toString();
                salAdv = SalaryAdvance.getText().toString();
                netSal = NetSalary.getText().toString();

                long newId = dbHandler.addEmployeeDetails(username, basSal, travAll, ot, salAdv, netSal);

                Toast.makeText(Employee_Salary.this, "salary add success. salary id: "+newId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Employee_Salary.this,Admin_Choose.class);
                startActivity(intent);
            }
        });

        NetSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double Salary=Double.parseDouble(BasicSalary.getText().toString());
                Double tra=Double.parseDouble(TravellingAllowance.getText().toString());
                Double Ot=Double.parseDouble(OverTime.getText().toString());
                Double salad=Double.parseDouble(SalaryAdvance.getText().toString());
                double tax;
                Double netsalary;

                if(Salary > 5000){
                    tax = Salary *10/100;
                    netsalary = ((Salary + tra) + Ot*100) - (salad + tax);



                }
                else if (Salary > 3000){
                    tax = Salary * 5/100;

                    netsalary = ((Salary + tra-tax) + Ot*100) - (salad + tax);

                }
                else {
                    tax = 0;
                    netsalary = ((Salary + tra-tax) + Ot*100) - (salad + tax);

                }

                NetSalary.setText(String.valueOf(netsalary));



            }
        });


        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Employee_Salary.this,Employee_Search.class);
                startActivity(intent);
            }
        });





    }
}
