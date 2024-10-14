package com.example.safesocietyalertsystem.Model;

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

import com.example.safesocietyalertsystem.DashboardScreen;
import com.example.safesocietyalertsystem.HumidityActivity;
import com.example.safesocietyalertsystem.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

import pl.droidsonroids.gif.GifImageView;

public class TemperatureActivity extends AppCompatActivity {

    MediaPlayer player;

    ImageView img_smoke_back,img_fire;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String fireValue,img;

    TextView tv_fire;
    GifImageView gif;

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        img_smoke_back=findViewById(R.id.img_smoke_back);

        img_fire=findViewById(R.id.img_fire);
        tv_fire=findViewById(R.id.tv_fire);
        btn_back=findViewById(R.id.btn_back);
        gif=findViewById(R.id.gif);

        img_smoke_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(TemperatureActivity.this, DashboardScreen.class);
                startActivity(backIntent);
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(TemperatureActivity.this,DashboardScreen.class);
                startActivity(backIntent);
                finish();
            }
        });


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CurrentData");
        DatabaseReference callref=myRef.child("1000");


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                fireValue=snapshot.child("TempSensor").getValue(String.class);
                img=snapshot.child("img").getValue(String.class);




                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] imageBytes = baos.toByteArray();
                imageBytes = Base64.decode(img, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                img_fire.setImageBitmap(decodedImage);

                tv_fire.setText("Today Temperature is "+ fireValue + " C");
//                    gif.setVisibility(View.VISIBLE);

//                    play();

//
//                else
//                {
//                    tv_fire.setText("Everything is Ok");
//                    gif.setVisibility(View.INVISIBLE);
//                    img_fire.setImageResource(R.drawable.okimage);
//                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                fireValue=snapshot.child("TempSensor").getValue(String.class);




//                if (fireValue.equals("1"))
//                {


                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] imageBytes = baos.toByteArray();
                imageBytes = Base64.decode(img, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                img_fire.setImageBitmap(decodedImage);
                tv_fire.setText("Today Temperature is "+ fireValue + " C");
//                    gif.setVisibility(View.VISIBLE);

//                    play();

//                }
//                else
//                {
//                    tv_fire.setText("Everything is Ok");
//                    img_fire.setImageResource(R.drawable.okimage);
//                    gif.setVisibility(View.INVISIBLE);
//                    stopPlayer();
//                }
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
}