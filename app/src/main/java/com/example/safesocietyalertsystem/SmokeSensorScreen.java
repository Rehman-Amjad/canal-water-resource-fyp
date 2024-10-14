package com.example.safesocietyalertsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

import pl.droidsonroids.gif.GifImageView;

public class SmokeSensorScreen extends AppCompatActivity {

    MediaPlayer player;

    ImageView img_smoke_back,img_fire;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String fireValue,img;

    TextView tv_fire;
    GifImageView gif;
    TextView tv_date,tv_time;

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoke_sensor_screen);

        img_smoke_back=findViewById(R.id.img_smoke_back);

        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);

        img_fire=findViewById(R.id.img_fire);
        tv_fire=findViewById(R.id.tv_fire);
        btn_back=findViewById(R.id.btn_back);

        img_smoke_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(SmokeSensorScreen.this,DashboardScreen.class);
                startActivity(backIntent);
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(SmokeSensorScreen.this,DashboardScreen.class);
                startActivity(backIntent);
                finish();
            }
        });


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("WaterData");
        DatabaseReference callref=myRef.child("Current");


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                fireValue=snapshot.child("TurbiditySensor").getValue(String.class);


                tv_date.setText(snapshot.child("Dated").getValue(String.class));
                tv_time.setText(snapshot.child("Timed").getValue(String.class));
                    tv_fire.setText("Turbidity Sensor: "+fireValue);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void play()
    {
        if (player == null)
        {
            player = MediaPlayer.create(SmokeSensorScreen.this, R.raw.smokealaem);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player.start();
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }

    public void stop(View v) {
        stopPlayer();
    }

    public void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlayer();
    }
}