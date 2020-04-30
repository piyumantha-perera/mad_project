package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.mad_project.Notification.CHANNEL_ID;

public class CreationUpdate_Clone extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    TextView full;
    Spinner spinner;
    TextView dDate;
    Button update;

    Button deliveryDate;

    ImageView home;

    DBHandler dbHandler;

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_update__clone);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        deliveryDate = findViewById(R.id.buttonDelDate);

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

        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreationUpdate_Clone.this, Customer_Choose.class);
                intent.putExtra("Name",user_name);
                startActivity(intent);
            }
        });


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

        deliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String date = (month+1) + "/" + dayOfMonth + "/" + year;
        dDate.setText(date);
    }

}
