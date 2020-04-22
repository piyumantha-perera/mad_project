package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class Customer_Profile extends AppCompatActivity {

    private static final String TAG = "Customer_Profile";

    private ArrayList<String> userName = new ArrayList<>();
    private ArrayList<String> userId = new ArrayList<>();
    private ArrayList<String> userCreation = new ArrayList<>();
    private ArrayList<String> deleteImageUrls = new ArrayList<>();
    private ArrayList<String> creationID = new ArrayList<>();

    Button creation, edit, update;
    TextView user, usrEmail, usrContact, usrAddress, logout;

    DBHandler dbHandler;

    Integer cnt = 0;

    ImageView drop;
    ImageView moveProfilePick;
    ImageView getPic;
    ImageView viewPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__profile);

        creation = findViewById(R.id.buttonProfileCreation);
        edit = findViewById(R.id.buttonProEdit);
        update = findViewById(R.id.buttonUpdate);

        moveProfilePick = findViewById(R.id.imageViewUploadProfle);
        getPic = findViewById(R.id.imageViewProImage);
        getPic = findViewById(R.id.imageViewProImage);
        viewPic = findViewById(R.id.imageViewProImage);

        dbHandler = new DBHandler(getApplicationContext());

        logout = findViewById(R.id.btn_logout);
        drop = findViewById(R.id.imageButtonDropdown);

        user = findViewById(R.id.textViewCusPUsername);
        usrEmail = findViewById(R.id.textViewCusPEmail);
        usrContact = findViewById(R.id.textViewCusPContact);
        usrAddress = findViewById(R.id.textViewCusPCompany);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");
        final String email = bn.getString("Email");
        final String contactNo = bn.getString("ContactNo");
        final String address = bn.getString("Address");
        final String password = bn.getString("Password");
        final String cnf_pass = bn.getString("CnfPassword");

        user.setText(username);
        usrEmail.setText(email);
        usrContact.setText(contactNo);
        usrAddress.setText(address);

        creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Profile.this, Customer_Choose.class);
                intent.putExtra("Name",username);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEmail = usrEmail.getText().toString();

                Cursor cursor = dbHandler.readThoughtDetails();
                String usrmail = "*";

                while (cursor.moveToNext()){
                    usrmail = cursor.getString(1);
                }
                if(usrmail.equals(userEmail)){
                    Intent intent = new Intent(Customer_Profile.this, Check.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Customer_Profile.this, Thought.class);
                    intent.putExtra("Email", userEmail);
                    startActivity(intent);
                }


            }
        });

        moveProfilePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = user.getText().toString();
                String email = usrEmail.getText().toString();
                String contact = usrContact.getText().toString();
                String addr = usrAddress.getText().toString();

                Intent intent = new Intent(Customer_Profile.this,UploadPicture.class);
                intent.putExtra("UserName", userName);
                intent.putExtra("Email", email);
                intent.putExtra("ContactNo", contact);
                intent.putExtra("Address", addr);
                intent.putExtra("Password",password);
                intent.putExtra("CnfPassword",cnf_pass);
                startActivity(intent);
            }
        });

        getPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = user.getText().toString();
                viewPic.setImageBitmap(dbHandler.getImage(uname));
            }
        });

        Log.d(TAG, "onCreate: started");

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (cnt == 0) {
                    initImageBitmaps();
                    cnt++;
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Customer_Profile.this);
                alertDialogBuilder.setTitle("Delete Creation!");
                alertDialogBuilder.setMessage("Are you want to, Delete or Update?");
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(Customer_Profile.this, Profile_Edit.class);
                        intent.putExtra("Name",username);
                        intent.putExtra("Email",email);
                        intent.putExtra("ContactNo",contactNo);
                        intent.putExtra("Address",address);
                        intent.putExtra("Password",password);
                        intent.putExtra("CnfPassword",cnf_pass);
                        startActivity(intent);
                    }
                });

                alertDialogBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Customer_Profile.this, Delete_User.class);
                        intent.putExtra("Name",username);
                        startActivity(intent);
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        Log.d(TAG,"onCreate: started.");
        setIncomingIntent();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Profile.this, CreationUpdate.class);
                intent.putExtra("Name",username);
                startActivity(intent);
            }
        });
    }

    public void setIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for the incoming intent.");

    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: started");
        initRecyclerView();
    }


    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");

        DBHandler dbHandler =  new DBHandler(getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        String username = user.getText().toString();

        ArrayList<String> list = new ArrayList<>();
        Cursor data = dbHandler.getListConents();

        int count = 1;

        while (data.moveToNext()){

            String name = data.getString(1);


            if (name.equals(username)){
                //userId.add(data.getString(0));

                userId.add(String.valueOf(count));
                userName.add(data.getString(5));
                userCreation.add(data.getString(2));
                creationID.add(data.getString(0));

                deleteImageUrls.add("https://img.icons8.com/ios-glyphs/30/000000/delete-forever.png");

                RecycleViewAdapter adapter = new RecycleViewAdapter(userName,userId,userCreation,deleteImageUrls,creationID, this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                count = count + 1;

            }
            if(count == 1){
                Toast.makeText(this, "This user have not Creations", Toast.LENGTH_SHORT).show();
            }

        }


    }
}
