package com.example.safesocietyalertsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safesocietyalertsystem.Model.TemperatureActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Thread.sleep;

public class DashboardScreen extends AppCompatActivity {

    NavigationView navMenu;
    ActionBarDrawerToggle toggle;
    DrawerLayout drayerLayout;

    Fragment temp=null;

    FirebaseDatabase database;
    DatabaseReference myRef;

    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);

        Toolbar toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        navMenu=findViewById(R.id.navMenu);
        drayerLayout=findViewById(R.id.drawerlayout);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("History");

    //    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Fragment_MainDashboard_cat()).commit();

        tv_result=findViewById(R.id.tv_result);

        toggle=new ActionBarDrawerToggle(this,drayerLayout,toolbar,R.string.app_name,R.string.app_name);
        drayerLayout.addDrawerListener(toggle);
        toggle.syncState();




        navMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {


//                    // tds
//                    case R.id.menu_Fire:
//                        Intent fireIntent=new Intent(DashboardScreen.this,FireSensorScreen.class);
//                        startActivity(fireIntent);
//                        drayerLayout.closeDrawer(GravityCompat.START);
//                        break;


                        //turbidity
                    case R.id.menu_Smoke:
                        Intent smokeIntent=new Intent(DashboardScreen.this,SmokeSensorScreen.class);
                        startActivity(smokeIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_Ultrasonic:
                        Intent ultraIntent=new Intent(DashboardScreen.this,UltraSensorScreeen.class);
                        startActivity(ultraIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                        //temperature
                    case R.id.menu_humidity:
                        Intent humidityIntent=new Intent(DashboardScreen.this,HumidityActivity.class);
                        startActivity(humidityIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

//                    case R.id.menu_door:
//                        Intent door=new Intent(DashboardScreen.this,DoorActivity.class);
//                        startActivity(door);
//                        drayerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case R.id.menu_temp:
//                        Intent temp=new Intent(DashboardScreen.this, TemperatureActivity.class);
//                        startActivity(temp);
//                        drayerLayout.closeDrawer(GravityCompat.START);
//                        break;

                    case R.id.menu_show_all_data:
                        Intent showIntent=new Intent(DashboardScreen.this,FirebaseShowData.class);
                        startActivity(showIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                        case R.id.menu_delete:
                        deleteHistory();
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.menu_logout:

                        Intent logIntent=new Intent(DashboardScreen.this,LoginScreen.class);
                        startActivity(logIntent);
                        finish();

                        Toast.makeText(DashboardScreen.this, "Logout", Toast.LENGTH_SHORT).show();
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                }



                return false;
            }
        });



    }

    private void deleteHistory()
    {
        myRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(DashboardScreen.this, "History Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}