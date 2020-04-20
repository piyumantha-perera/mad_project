package com.example.mad_project.Customer_Details;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Customer_Details.CreationUpdate_LeafClone;
import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;


public class FragmentLeaflet extends Fragment {

    EditText qty, url, des;
    TextView total;
    Button next;

    DBHandler dbHandler;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_leaflet, container, false);

        dbHandler = new DBHandler(getContext());

        qty = view.findViewById(R.id.editTextLeafletQty);
        total = view.findViewById(R.id.textViewLeafletTotal);
        url = view.findViewById(R.id.editTextImageUrl);
        des = view.findViewById(R.id.editTextCreationDes);

        next = view.findViewById(R.id.buttonLeafletNext);

        Bundle bn = getArguments();
        final String creationID = bn.getString("Creation ID");

        String quantity = null, images = null, description = null;
        String dDate = null;
        String getType = null;
        String creationName = null;
        String userName = null;

        Cursor cursor = dbHandler.readCreationDetails();
        while (cursor.moveToNext()){
            String crId = cursor.getString(0);
            if (crId.equals(creationID)){
                userName = cursor.getString(1);
                creationName = cursor.getString(2);
                images = cursor.getString(5);
                description = cursor.getString(6);
                quantity = cursor.getString(7);
                getType = cursor.getString(9);
                dDate = cursor.getString(10);

            }
        }
        url.setText(images);
        des.setText(description);
        qty.setText(quantity);

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(qty.getText().toString());

                if (quantity >= 500){
                    double price = quantity * 3.0;
                    total.setText(String.valueOf("Rs."+price));
                }
                else {
                    Toast.makeText(getActivity(), "Enter the value of quantity is more than 500", Toast.LENGTH_SHORT).show();
                    qty.setText("");
                    total.setText("");
                }
            }
        });

        final String finalGetType = getType;
        final String finalDDate = dDate;

        final String finalUserName = userName;
        final String finalCreationName = creationName;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String quantity, price, image, description;
                quantity = qty.getText().toString();
                price = total.getText().toString();
                image = url.getText().toString();
                description = des.getText().toString();

                Intent intent = new Intent(getActivity(), CreationUpdate_LeafClone.class);
                intent.putExtra("Creation_ID", creationID);
                intent.putExtra("User_Name", finalUserName);
                intent.putExtra("Creation_Name", finalCreationName);
                intent.putExtra("Quantity", quantity);
                intent.putExtra("Price", price);
                intent.putExtra("ImageUrl", image);
                intent.putExtra("Description", description);
                intent.putExtra("GetType", finalGetType);
                intent.putExtra("DelDate", finalDDate);
                startActivity(intent);
            }
        });

        return view;
    }

}
