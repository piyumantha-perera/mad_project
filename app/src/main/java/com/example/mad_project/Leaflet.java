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

    EditText qty, url, des;
    TextView total;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaflet);

        qty = findViewById(R.id.editTextLeafletQty);
        total = findViewById(R.id.textViewLeafletTotal);
        url = findViewById(R.id.editTextImageUrl);
        des = findViewById(R.id.editTextCreationDes);

        next = findViewById(R.id.buttonLeafletNext);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");
        final String c_type = bn.getString("creation_type");

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
                String quantity, price, image, description;
                quantity = qty.getText().toString();
                price = total.getText().toString();
                image = url.getText().toString();
                description = des.getText().toString();

                Intent intent = new Intent(Leaflet.this, Leaflet_Clone.class);
                intent.putExtra("Name",username);
                intent.putExtra("creation_type", c_type);
                intent.putExtra("Quantity", quantity);
                intent.putExtra("Price", price);
                intent.putExtra("ImageUrl", image);
                intent.putExtra("Description", description);
                startActivity(intent);
            }
        });
    }
}
