package com.example.mad_project.Customer_Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

import java.util.Calendar;

import static com.example.mad_project.Notification.CHANNEL_ID;

public class NameBoard_Clone extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    TextView full;
    EditText delDate;
    Button add;

    Button deliveryDate;
    TextView dDate;

    DBHandler dbHandler;

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_board__clone);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        dDate = findViewById(R.id.editTextCreDelDate);
        deliveryDate = findViewById(R.id.buttonDelDate);

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerGetingType);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.customer_creation_get, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        full = findViewById(R.id.textViewBannerTotal);

        add = findViewById(R.id.buttonCreationAdd);

        dbHandler = new DBHandler(getApplicationContext());

        Bundle bn = getIntent().getExtras();
        final String username = bn.getString("Name");
        final String c_type = bn.getString("creation_type");
        final String amount = bn.getString("Price");
        final String length = bn.getString("Length");
        final String width = bn.getString("Width");
        final String imageUrl = bn.getString("Url");
        final String description = bn.getString("Description");
        final String quantity = bn.getString("Quantity");

        full.setText("Rs."+String.valueOf(amount));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deliveryDate = dDate.getText().toString();
                String getType = spinner.getSelectedItem().toString();

                long newID = dbHandler.addCreationDetails(username, c_type, length, width, imageUrl, description, quantity, "Rs."+amount, getType, deliveryDate);
                if (newID > 0){
                    Toast.makeText(NameBoard_Clone.this, "Creation added Successfull", Toast.LENGTH_SHORT).show();

                    String full_total = full.getText().toString();

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(NameBoard_Clone.this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_announcement_black_24dp)
                            .setContentTitle("RUSH Advertising Notification")
                            .setContentText("Your creations' total amount is " + full_total)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE);

                    notificationManagerCompat.notify(1, builder.build());
                }
                else {
                    Toast.makeText(NameBoard_Clone.this, "Creation not added", Toast.LENGTH_SHORT).show();
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
