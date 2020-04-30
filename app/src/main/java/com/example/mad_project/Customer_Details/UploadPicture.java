package com.example.mad_project.Customer_Details;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mad_project.Database.DBHandler;
import com.example.mad_project.R;

public class UploadPicture extends AppCompatActivity {

    ImageView takePhoto;

    ImageView viewPhoto;
    Button upload;

    public static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    DBHandler dbHandler;

    String username = null;
    String email = null;
    String contact = null;
    String address = null;
    String password = null;
    String cnf_pass = null;

    ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);

        takePhoto = findViewById(R.id.imageViewAddProfile);
        viewPhoto = findViewById(R.id.imageViewProfileImage);
        upload = findViewById(R.id.buttonProfileUpload);;

        dbHandler = new DBHandler(getApplicationContext());
        Bundle bn = getIntent().getExtras();
        username = bn.getString("UserName");
        email = bn.getString("Email");
        contact = bn.getString("ContactNo");
        address = bn.getString("Address");
        password = bn.getString("Password");
        cnf_pass = bn.getString("CnfPassword");

        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadPicture.this, Customer_Choose.class);
                intent.putExtra("Name",username);
                startActivity(intent);
            }
        });
    }
    public void chooseImage(View objectView){
        try {

            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,PICK_IMAGE_REQUEST);

        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                viewPhoto.setImageBitmap(imageToStore);

            }
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void storeImage(View view){
        try {
            if (!username.isEmpty() && viewPhoto.getDrawable() != null && imageToStore != null){
                dbHandler.storeImage(new Profile(username,imageToStore));
                Intent intent = new Intent(this,Customer_Profile.class);
                intent.putExtra("Name",username);
                intent.putExtra("Email",email);
                intent.putExtra("ContactNo",contact);
                intent.putExtra("Address",address);
                intent.putExtra("Password",password);
                intent.putExtra("CnfPassword",cnf_pass);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Please select image name and image", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
