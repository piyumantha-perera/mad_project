package com.example.mad_project.Employee_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = Username.getText().toString();
                String basicSalary = BasicSal.getText().toString();
                String travellingAllowance = TravAll.getText().toString();
                String overTime = Ot.getText().toString();
                String salaryAdvance = SalAd.getText().toString();
                String netSalary = NetSa.getText().toString();

                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                Boolean update = dbHandler.updateEmpSalary(userName,basicSalary, travellingAllowance, overTime, salaryAdvance, netSalary,date);

                if(update){
                    Toast.makeText(Employee_SalaryUpdate.this, "Salary Updated", Toast.LENGTH_SHORT).show();

                    Double Salary=Double.parseDouble(BasicSal.getText().toString());
                    Double tra=Double.parseDouble(TravAll.getText().toString());
                    Double ot=Double.parseDouble(Ot.getText().toString());
                    Double salad=Double.parseDouble(SalAd.getText().toString());
                    double tax;
                    Double netsalary;

                    if(Salary > 5000){
                        tax = Salary *10/100;
                        netsalary = ((Salary + tra) + ot*100) - (salad + tax);



                    }
                    else if (Salary > 3000){
                        tax = Salary * 5/100;

                        netsalary = ((Salary + tra-tax) + ot*100) - (salad + tax);

                    }
                    else {
                        tax = 0;
                        netsalary = ((Salary + tra-tax) + ot*100) - (salad + tax);

                    }

                    NetSa.setText(String.valueOf(netsalary));

                }
                else {
                    Toast.makeText(Employee_SalaryUpdate.this, "Salary Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
