package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LightBoard extends AppCompatActivity {

    EditText len;
    EditText wid;
    EditText qty;

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_board);

        len = findViewById(R.id.editTextBannerLen);
        wid = findViewById(R.id.editTextBannerWid);
        qty = findViewById(R.id.editTextBannerQty);

        next = findViewById(R.id.buttonBannerNext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length, width, quantity;

                length = Integer.parseInt(len.getText().toString());
                width = Integer.parseInt(wid.getText().toString());
                quantity = Integer.parseInt(qty.getText().toString());

                Double calculate = ((length *width) * 500.0) * quantity;


                String amount = calculate.toString();
                Intent intent = new Intent(LightBoard.this,LightBoard_Clone.class);
                intent.putExtra("price",amount);
                startActivity(intent);
            }
        });
    }
}
