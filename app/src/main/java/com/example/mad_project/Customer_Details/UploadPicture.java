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
        final String password = bn.getString("Password");
        final String cnf_pass = bn.getString("CnfPassword");
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
