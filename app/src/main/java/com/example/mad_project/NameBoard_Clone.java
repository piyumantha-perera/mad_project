package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NameBoard_Clone extends AppCompatActivity {

    TextView full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_board__clone);

        full = findViewById(R.id.textViewBannerTotal);

        Bundle bn = getIntent().getExtras();
        String amount = bn.getString("price");

        full.setText("Rs."+String.valueOf(amount));

    }
}
