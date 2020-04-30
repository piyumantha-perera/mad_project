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

import com.example.mad_project.Customer_Details.CreationUpdate_Clone;
import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

public class FragmentBoard extends Fragment {

    EditText len;
    EditText wid;
    EditText qty, imagesUrl, des;

    Button next;
    DBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_board, container, false);

        dbHandler = new DBHandler(getContext());

        len = view.findViewById(R.id.editTextBannerLen);
        wid = view.findViewById(R.id.editTextBannerWid);
        qty = view.findViewById(R.id.editTextBannerQty);
        imagesUrl = view.findViewById(R.id.editTextCreationUrl);
        des = view.findViewById(R.id.editTextBannerDes);
        next = view.findViewById(R.id.buttonBannerNext);

        Bundle bn = getArguments();
        final String creationID = bn.getString("Creation ID");

        String width = null, length = null, quantity = null, images = null, description = null;
        String dDate = null;
        String getType = null;
        String creationName = null;
        String userName = null;

        Cursor cursor = dbHandler.readCreationDetails();
        while (cursor.moveToNext()){
            String crId = cursor.getString(0);
            if (crId.equals(creationID)){
                creationName = cursor.getString(2);
                userName = cursor.getString(1);
                length = cursor.getString(3);
                width = cursor.getString(4);
                images = cursor.getString(5);
                description = cursor.getString(6);
                quantity = cursor.getString(7);
                getType = cursor.getString(9);
                dDate = cursor.getString(10);

            }
        }

        len.setText(length);
        wid.setText(width);
        imagesUrl.setText(images);
        des.setText(description);
        qty.setText(quantity);

        final String finalCreationName = creationName;
        final String finalDDate = dDate;
        final String finalGetType = getType;

        final String finalUserName = userName;
        final String finalCreationName1 = creationName;
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

                length = Integer.parseInt(len.getText().toString());
                width = Integer.parseInt(wid.getText().toString());
                quantity = Integer.parseInt(qty.getText().toString());

                Double calculate = null;

                if (finalCreationName.equals("Banner")){
                    calculate = ((length *width) * 100.0) * quantity;
                }
                else if (finalCreationName.equals("Name Board")){
                    calculate = ((length *width) * 300.0) * quantity;
                }
                else
                    calculate = ((length *width) * 500.0) * quantity;

                String amount = calculate.toString();

                Intent intent = new Intent(getActivity(), CreationUpdate_Clone.class);
                intent.putExtra("Cretion_id", creationID);
                intent.putExtra("User_Name", finalUserName);
                intent.putExtra("Cretion_Name", finalCreationName1);
                intent.putExtra("Length", crLength);
                intent.putExtra("Width", crWidth);
                intent.putExtra("ImageUrl", crImages);
                intent.putExtra("Description", crdescription);
                intent.putExtra("Quantity", crQty);
                intent.putExtra("Amount", amount);
                intent.putExtra("GetType", finalGetType);
                intent.putExtra("DelDate", finalDDate);
                startActivity(intent);
            }
        });
        return view;

    }
}
