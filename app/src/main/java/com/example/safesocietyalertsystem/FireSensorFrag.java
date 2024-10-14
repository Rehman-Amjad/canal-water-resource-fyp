package com.example.safesocietyalertsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safesocietyalertsystem.Model.SensorAdapter;
import com.example.safesocietyalertsystem.Model.User;
import com.example.safesocietyalertsystem.Model.UserAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


public class FireSensorFrag extends Fragment {



    MediaPlayer player;



    FirebaseDatabase database;
    DatabaseReference myRef;
   

    String fireValue;
    private ChildEventListener MyChildEventListener;

    @Override
    public void onDestroy() {
        super.onDestroy();
        myRef.removeEventListener(MyChildEventListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fire_sensor, container, false);



        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("OnceData");
        DatabaseReference callref=myRef.child("100");




       callref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
                fireValue=snapshot.child("FireSensor").getValue(String.class);
               Toast.makeText(getActivity(), ""+fireValue, Toast.LENGTH_SHORT).show();

              if (fireValue.equals("1"))
              {
                  play();
              }

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });







        return v;
    }

    public void play()
    {
        if (player == null)
        {
            player = MediaPlayer.create(getActivity(), R.raw.firealaram);
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
            Toast.makeText(getActivity(), "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlayer();
    }


}