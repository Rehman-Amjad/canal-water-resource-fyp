package com.example.safesocietyalertsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;

public class UltraSensorScreeen extends AppCompatActivity {


    ImageView img_ultra_back,img_fire;


    FirebaseDatabase database;
    DatabaseReference myRef;
    String fireValue,img;

    TextView tv_fire,tv_waterValue;

    Button btn_back;
    TextView tv_date,tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra_sensor_screeen);

        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);

        img_ultra_back=findViewById(R.id.img_ultra_back);
        img_fire=findViewById(R.id.img_fire);
        tv_fire=findViewById(R.id.tv_fire);
        btn_back=findViewById(R.id.btn_back);
        tv_waterValue=findViewById(R.id.tv_waterValue);

        database = FirebaseDatabase.getInstance();





        img_ultra_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(UltraSensorScreeen.this,DashboardScreen.class);
                startActivity(backIntent);
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(UltraSensorScreeen.this,DashboardScreen.class);
                startActivity(backIntent);
                finish();
            }
        });



        myRef = database.getReference("WaterData");
        DatabaseReference callref = myRef.child("Current");

        myRef.addChildEventListener(new ChildEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                 fireValue = snapshot.child("Waterlavel").getValue(String.class);
                String date = snapshot.child("Dated").getValue(String.class);
                String time = snapshot.child("Timed").getValue(String.class);
                tv_waterValue.setText("Water Level is "+fireValue+"%");

                Log.d("WATERLEVEL", "onChildAdded: "+fireValue);

//
                tv_date.setText(date != null ? date : "Unknown date");
                tv_time.setText(time != null ? time : "Unknown time");

                try {

                    assert fireValue != null;
                    switch (fireValue) {
                        case "100":
                            tv_fire.setText("Water Level in Tank is full");
                            break;
                        case "75":
                            tv_fire.setText("Water Level in Tank Near to full");
                            break;
                        case "50":
                            tv_fire.setText("Water level in Tank Normal");
                            break;
                        case "25":
                            tv_fire.setText("Water level in Tank Very Low");
                            break;
                        default:
                            tv_fire.setText("Water level in Tank Empty");
                            break;
                    }
                } catch (NumberFormatException e) {
                    tv_fire.setText("Invalid water level data");
                }

                // Load image once (if it's static)
                Glide.with(UltraSensorScreeen.this)
                        .load(R.drawable.water_level)
                        .into(img_fire);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                fireValue = snapshot.child("Waterlavel").getValue(String.class);
                String date = snapshot.child("Dated").getValue(String.class);
                String time = snapshot.child("Timed").getValue(String.class);
//
//
//                // Update the date and time
                tv_date.setText(date != null ? date : "Unknown date");
                tv_time.setText(time != null ? time : "Unknown time");

                // Parse the water level and display appropriate message
                try {

                    assert fireValue != null;
                    switch (fireValue) {
                        case "100":
                            tv_fire.setText("Water Level in Tank is full");
                            break;
                        case "75":
                            tv_fire.setText("Water Level in Tank Near to full");
                            break;
                        case "50":
                            tv_fire.setText("Water level in Tank Normal");
                            break;
                        case "25":
                            tv_fire.setText("Water level in Tank Very Low");
                            break;
                        default:
                            tv_fire.setText("Water level in Tank Empty");
                            break;
                    }
                } catch (NumberFormatException e) {
                    tv_fire.setText("Invalid water level data");
                }

                // Load image once (if it's static)
                Glide.with(UltraSensorScreeen.this)
                        .load(R.drawable.water_level)
                        .into(img_fire);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // Handle child removal if needed
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Handle child moved if needed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });



    }
    @SuppressLint("SetTextI18n")
    private void updateWaterLevel(DataSnapshot snapshot) {

    }

}