package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.mad_project.Notification.CHANNEL_ID;

public class CreationUpdate_Clone extends AppCompatActivity {

    TextView full;
    Spinner spinner;
    EditText dDate;
    Button update;

    DBHandler dbHandler;

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_update__clone);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        dbHandler = new DBHandler(getApplicationContext());

        full = findViewById(R.id.textViewBannerTotal);
        spinner = findViewById(R.id.spinnerGetingType);
        dDate = findViewById(R.id.editTextCreDelDate);
        update = findViewById(R.id.buttonUpdate);

        Bundle bn = getIntent().getExtras();
        final String cretion_id = bn.getString("Cretion_id");
        final String user_name = bn.getString("User_Name");
        final String cretion_name = bn.getString("Cretion_Name");
        final String length = bn.getString("Length");
        final String width = bn.getString("Width");
        final String imageUrl = bn.getString("ImageUrl");
        final String description = bn.getString("Description");
        final String quantity = bn.getString("Quantity");
        final String amount = bn.getString("Amount");
        final String getType = bn.getString("GetType");
        final String delDate = bn.getString("DelDate");

        final List<String> gettingTypes = new ArrayList<>();
        gettingTypes.add("Taking");
        gettingTypes.add("Delivery");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gettingTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        full.setText(amount);
        dDate.setText(delDate);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String deliveryDate = dDate.getText().toString();
                String get = spinner.getSelectedItem().toString();

                boolean res = dbHandler.updateCreationInfo(cretion_id, user_name, cretion_name, length, width, imageUrl, description, quantity, amount, get, deliveryDate);
                if (res){
                    Toast.makeText(CreationUpdate_Clone.this, "Creation Update Successfull", Toast.LENGTH_SHORT).show();

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(CreationUpdate_Clone.this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_announcement_black_24dp)
                            .setContentTitle("RUSH Advertising Notification")
                            .setContentText("Your creations' total amount is " + amount)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE);

                    notificationManagerCompat.notify(1, builder.build());
                }
                else {
                    Toast.makeText(CreationUpdate_Clone.this, "Creation not added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
