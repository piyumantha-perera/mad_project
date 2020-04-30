package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mad_project.R;

public class Banner extends AppCompatActivity {

    EditText len;
    EditText wid;
    EditText qty, imagesUrl, des;

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        len = findViewById(R.id.editTextBannerLen);
        wid = findViewById(R.id.editTextBannerWid);
        qty = findViewById(R.id.editTextBannerQty);
        imagesUrl = findViewById(R.id.editTextCreationUrl);
        des = findViewById(R.id.editTextBannerDes);

        next = findViewById(R.id.buttonBannerNext);

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");
        final String c_type = bn.getString("creation_type");


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String crLength, crWidth, crQty, crImages, crdescription;
                crLength = len.getText().toString();
                crWidth = wid.getText().toString();
                crQty = qty.getText().toString();
                crImages = imagesUrl.getText().toString();
                crdescription = des.getText().toString();

                int length, width, quantity;

                if (crLength.isEmpty() || crWidth.isEmpty() || crQty.isEmpty() || crdescription.isEmpty()|| crImages.isEmpty()) {
                    Toast.makeText(Banner.this, "Please Fill all text feild.", Toast.LENGTH_SHORT).show();
                }
                else{
                    length = Integer.parseInt(len.getText().toString());
                    width = Integer.parseInt(wid.getText().toString());
                    quantity = Integer.parseInt(qty.getText().toString());

                    Double calculate = ((length *width) * 100.0) * quantity;


                    String amount = calculate.toString();
                    Intent intent = new Intent(Banner.this, Banner_Clone.class);
                    intent.putExtra("Name",username);
                    intent.putExtra("creation_type", c_type);
                    intent.putExtra("Length",crLength);
                    intent.putExtra("Width",crWidth);
                    intent.putExtra("Url",crImages);
                    intent.putExtra("Description",crdescription);
                    intent.putExtra("Quantity",crQty);
                    intent.putExtra("Price",amount);
                    startActivity(intent);
                }


            }
        });


    }

}
