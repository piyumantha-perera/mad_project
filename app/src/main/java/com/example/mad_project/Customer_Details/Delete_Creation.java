package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

public class Delete_Creation extends AppCompatActivity {

    public static final String TAG = "CreationDeleteActivity";

    TextView test;
    Button delete, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__creation);

        test = findViewById(R.id.textView65);
        delete = findViewById(R.id.buttonCreationDelete);
        cancel = findViewById(R.id.buttonDeleteCancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String username = test.getText().toString();
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                Cursor res = dbHandler.readCreationDetails();
                String id = test.getText().toString();
                String user = null;
                String email = null;
                String contact = null;
                String addr = null;
                String password = null;
                String cnf_pass = null;

                while (res.moveToNext()){
                    String crId = res.getString(0);
                    if(crId.equals(id)){
                        user = res.getString(1);
                    }
                }

                Intent intent = new Intent(Delete_Creation.this,Customer_Profile.class);
                intent.putExtra("Name",user);
                intent.putExtra("Email",email);
                intent.putExtra("ContactNo",contact);
                intent.putExtra("Address",addr);
                intent.putExtra("Password",password);
                intent.putExtra("CnfPassword",cnf_pass);
                startActivity(intent);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Delete_Creation.this);
                alertDialogBuilder.setTitle("Delete Creation!");
                alertDialogBuilder.setMessage("Are you Sure, You want to Delete?");
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DBHandler dbHandler = new DBHandler(getApplicationContext());
                        String creationId = test.getText().toString();

                        Boolean delete = dbHandler.deleteCreationInfo(creationId);

                        if (delete){
                            Toast.makeText(Delete_Creation.this, "Creation Deleted", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Delete_Creation.this, "Creation Details not Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Delete Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        Log.d(TAG,"onCreate: started.");
        setIncomingIntent();
    }

    public void setIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for the incoming intent.");

        if(getIntent().hasExtra("creation_id")){
            String id = getIntent().getStringExtra("creation_id");
            test.setText(id);
        }
    }
}
