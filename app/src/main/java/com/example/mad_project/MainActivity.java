package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mad_project.R;

public class MainActivity extends AppCompatActivity {

    TextView text, title1, title2;
    ImageView pic;

    private static int SPLASH_SCREEN = 3000;


    Animation frombottom, fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        frombottom = AnimationUtils.loadAnimation(MainActivity.this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fromtop);

        text = findViewById(R.id.textViewDes);
        title1 = findViewById(R.id.textviewTitle1);
        title2 = findViewById(R.id.textViewTitle2);
        pic = findViewById(R.id.imageViewHome);

        title1.setAnimation(fromtop);
        title2.setAnimation(fromtop);
        pic.setAnimation(frombottom);
        text.setAnimation(frombottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Check.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
