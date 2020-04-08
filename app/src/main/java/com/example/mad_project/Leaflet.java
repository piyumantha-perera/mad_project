package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Leaflet extends AppCompatActivity {

    EditText qty;
    TextView total;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaflet);

        qty = findViewById(R.id.editTextLeafletQty);
        total = findViewById(R.id.textViewLeafletTotal);

        next = findViewById(R.id.buttonLeafletNext);

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(qty.getText().toString());

                if (quantity >= 500){
                    double price = quantity * 3.0;
                    total.setText(String.valueOf("Rs."+price));
                }
                else {
                    Toast.makeText(Leaflet.this, "Enter the value of quantity is more than 500", Toast.LENGTH_SHORT).show();
                    qty.setText("");
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Leaflet.this, Leaflet_Clone.class);
                startActivity(intent);
            }
        });
    }
}
