package com.example.safesocietyalertsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class HumidityActivity extends AppCompatActivity {

    private ImageView imgSmokeBack;
    private TextView tvFire,tv_value;
    private Button btnPumpOn, btnPumpOff, btnBack;

    private FirebaseDatabase database;
    private DatabaseReference pumpRef;
    private DatabaseReference sensorRef;

    // Declare a handler to schedule the 30-second delay
    private Handler handler = new Handler();

    String invoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity);

        // Initialize UI components
        imgSmokeBack = findViewById(R.id.img_smoke_back);
        tvFire = findViewById(R.id.tv_fire);
        btnPumpOn = findViewById(R.id.btn_pumpOn);
        btnPumpOff = findViewById(R.id.btn_pumpOff);
        btnBack = findViewById(R.id.btn_back);
        tv_value = findViewById(R.id.tv_value);

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance();
        pumpRef = database.getReference("WaterPump");
        sensorRef = database.getReference("Msg");

        btnPumpOn.setEnabled(false);

        // Fetch real-time sensor data from Firebase and display in tvFire
        fetchSensorData();
        fetchPumpValue();

        imgSmokeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnPumpOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Turn the pump ON in Firebase
                if(invoke.equals("1")){
                    Toast.makeText(HumidityActivity.this, "Pump Cannot On Due to Turbidity Value", Toast.LENGTH_SHORT).show();
                }else{
                    pumpRef.setValue("0");
                    Toast.makeText(HumidityActivity.this, "Pump is now ON", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPumpOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Turn the pump OFF in Firebase
                pumpRef.setValue("1");
                Toast.makeText(HumidityActivity.this, "Pump is now OFF", Toast.LENGTH_SHORT).show();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        monitorPumpState();

    }

    private void fetchPumpValue() {
        pumpRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sensorData = dataSnapshot.getValue(String.class);
                if (sensorData != null) {
                  if(sensorData.equals("0")){
                      tvFire.setText("Pump is on");
                  }else{
                      tvFire.setText("Pump is Off");
                  }
                } else {
                    tvFire.setText("Unable to fetch data.");
                }
                btnPumpOn.setEnabled(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HumidityActivity.this, "Error fetching sensor data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchSensorData() {
        sensorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sensorData = dataSnapshot.child("PumpMessage").getValue(String.class);
                 invoke = dataSnapshot.child("PumpInvoke").getValue(String.class);
                tv_value.setText(sensorData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HumidityActivity.this, "Error fetching sensor data", Toast.LENGTH_SHORT).show();
            }
        });
    }



//    // Method to check tvFire's text and turn off the pump if necessary
//    private void checkFireTextAndAutoOff() {
//        String fireText = tvFire.getText().toString();
//        if (fireText.startsWith("No")) {
//            // Turn the pump OFF in Firebase
//            pumpRef.setValue("0");
//            Toast.makeText(HumidityActivity.this, "Pump is now OFF (Auto-Off)", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void updatePumpStatus(String status) {
//        Map<String, Object> pumpUpdates = new HashMap<>();
//        pumpUpdates.put("WaterPump", status);  // Update the status field in PumpStatus node
//        pumpRef.updateChildren(pumpUpdates);  // Update Firebase with new status
//    }
}