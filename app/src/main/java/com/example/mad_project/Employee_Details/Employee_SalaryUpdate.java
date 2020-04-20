package com.example.mad_project.Employee_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

import java.util.List;

public class Employee_SalaryUpdate extends AppCompatActivity {

    TextView Username;
    EditText BasicSal,TravAll,Ot,SalAd,NetSa;
    Button Update;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__salary_update);

        Username=findViewById(R.id.textViewSalUpdateNme);
        BasicSal=findViewById(R.id.editTextSalUpdateBas);
        TravAll=findViewById(R.id.editTextSalUpdateTraAll);
        Ot=findViewById(R.id.editTextSalUpdateOt);
        SalAd=findViewById(R.id.editTextSalUpdateSalAd);
        NetSa=findViewById(R.id.editTextSalUpdateNetSal);
        Update=findViewById(R.id.buttonSalUpdate);

        dbHandler = new DBHandler(getApplicationContext());


         Bundle bn = getIntent().getExtras();

         String userName = bn.getString("UserName");
         String basicSalary = bn.getString("BasicSalary");
         String travellingAllowance = bn.getString("TravellingAllowance");
         String overTime = bn.getString("OverTime");
         String salaryAdvance = bn.getString("SalaryAdvance");
         String netSalary = bn.getString("NetSalary");

        Username.setText(userName);
        BasicSal.setText(basicSalary);
        TravAll.setText(travellingAllowance);
        Ot.setText(overTime);
        SalAd.setText(salaryAdvance);
        NetSa.setText(netSalary);



    }
}
