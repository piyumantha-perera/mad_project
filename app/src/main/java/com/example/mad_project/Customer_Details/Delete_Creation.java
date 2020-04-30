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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Check;
import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

public class Delete_Creation extends AppCompatActivity {

    TextView test;
    Button delete;
    ImageView home;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__creation);

        dbHandler = new DBHandler(getApplicationContext());

        test = findViewById(R.id.textView65);
        delete = findViewById(R.id.buttonCreationDelete);

        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String creationId = test.getText().toString();
                String id = null;
                String name = null;

                Cursor res = dbHandler.readCreationDetails();
                while (res.moveToNext()){
                    id = res.getString(0);
                    if (id.equals(creationId)){
                        name = res.getString(1);
                    }
                }
                Intent intent = new Intent(Delete_Creation.this, Customer_Choose.class);
                intent.putExtra("Name",name);
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

        setIncomingIntent();
    }

    public void setIncomingIntent(){

        if(getIntent().hasExtra("creation_id")){
            String id = getIntent().getStringExtra("creation_id");
            test.setText(id);
        }
    }

}
