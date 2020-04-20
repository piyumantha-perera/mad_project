package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mad_project.R;

public class MainActivity extends AppCompatActivity {

    //TextView text;

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //text = findViewById(R.id.textView3);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }

    public void progressAnimation (){
        ProgressBarAnimation amin = new ProgressBarAnimation(this, progressBar , textView , 0f,  100f);
        amin.setDuration(6000);
        progressBar.setAnimation(amin);
    }

    }
