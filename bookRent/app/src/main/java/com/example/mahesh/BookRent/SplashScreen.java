package com.example.mahesh.BookRent;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.logo);
        ObjectAnimator y=ObjectAnimator.ofFloat(logo,"y",600);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(logo, View.ALPHA,0.0f,1.0f);
        alpha.setDuration(1500);
        y.setDuration(1500);
        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.playTogether(alpha,y);
        animatorSet.start();

        //New Activity will start after 3000 milliseconds
       new Timer().schedule(new TimerTask() {
           @Override
           public void run() {
               Intent intent = new Intent(SplashScreen.this,LoginPage.class);
               startActivity(intent);
           }
       },3000);

    }
}
