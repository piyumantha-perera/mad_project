package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Banner_Clone extends AppCompatActivity {


    EditText qty;
    String quantity;
    TextView full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner__clone);


        full = findViewById(R.id.textViewBannerTotal);

        Bundle bn = getIntent().getExtras();
        String amount = bn.getString("price");

        full.setText("Rs."+String.valueOf(amount));


    }
}
