package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Check;
import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

public class Thought extends AppCompatActivity {

    TextView email;
    RatingBar rating;
    EditText thought;
    Button submit;

    ImageView home;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought);

        dbHandler = new DBHandler(getApplicationContext());

        email = findViewById(R.id.textViewThoughtEmail);
        rating = findViewById(R.id.ratingBar);
        thought = findViewById(R.id.editTextThought);
        submit = findViewById(R.id.buttonThoughtSubmit);

        Bundle bn = getIntent().getExtras();
        String usrEmail = bn.getString("Email");
        final String username = bn.getString("Name");

        email.setText(usrEmail);

        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thought.this, Customer_Choose.class);
                intent.putExtra("Name",username);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usrmail = email.getText().toString();
                String rate = String.valueOf(rating.getRating());
                String feedback = thought.getText().toString();

                double rt = Double.parseDouble(rate) * 20;

                long newId = dbHandler.addThoughtDetails(usrmail, String.valueOf(rt + "%"), feedback);
                if (newId > 0){
                    Toast.makeText(Thought.this, "Thought Details added. Thought ID: "+newId, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Thought.this, Check.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Thought.this, "Thought Details not added.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
