package com.example.healthapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {

    ImageView logo, titleText, runningImg;
    LottieAnimationView lottieAnimationView;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        
        logo = findViewById(R.id.logo1);
        titleText = findViewById(R.id.title_text);
        runningImg = findViewById(R.id.bdImg);
        lottieAnimationView = findViewById(R.id.lottie);


        runningImg.animate().translationY(-3600).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(3600).setDuration(1000).setStartDelay(4000);
        titleText.animate().translationY(3600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(3600).setDuration(1000).setStartDelay(4000);

        timer =new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, login.class);
                startActivity(intent);
                finish();
            }
        }, 4800);


    }
}