package com.example.safesocietyalertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView=findViewById(R.id.textView);
        img1=findViewById(R.id.img1);


        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.myanimation);
        textView.setAnimation(myanim);

        Glide.with(MainActivity.this)
                .load(R.drawable.splash_image)
                .into(img1);



        Thread mythread=new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(MainActivity.this,LoginScreen.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mythread.start();


    }
}