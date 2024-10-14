package com.example.safesocietyalertsystem.Model;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.safesocietyalertsystem.R;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private Context context;
    private List<User> mDatalist;

    public UserAdapter(Context context, List<User> mDatalist) {
        this.context = context;
        this.mDatalist = mDatalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);


        return new MyViewHolder(myview);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user=mDatalist.get(position);

        holder.tv_tds.setVisibility(View.GONE);
        holder.tv_temp.setVisibility(View.GONE);

        holder.tvId.setText("ID: "+user.getId());
        if(user.getWaterlavel().equals("100")){
            holder.tv_waterlevel.setText("Water Level in Tank is full");
        }else  if(user.getWaterlavel().equals("75")){
            holder.tv_waterlevel.setText("Water Level in Tank Near to full");
        }else  if(user.getWaterlavel().equals("50")){
            holder.tv_waterlevel.setText("Water level in Tank Normal");
        }else  if(user.getWaterlavel().equals("25")){
            holder.tv_waterlevel.setText("Water level in Tank Very Low");
        }else{
            holder.tv_waterlevel.setText("Water Level in Tank Empty");
        }
        holder.tv_turb.setText("Turbidity: "+user.getTurbiditySensor());
        holder.tv_date.setText("Date: "+user.getDated());
        holder.tv_time.setText("Time: "+user.getTimed());



    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvId,tv_tds,tv_turb,tv_waterlevel,tv_temp,tv_date,tv_time;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.card_image);
            tvId=itemView.findViewById(R.id.tvId);
            tv_tds=itemView.findViewById(R.id.tvFire_Sensor);
            tv_turb=itemView.findViewById(R.id.tv_LDR);
            tv_waterlevel=itemView.findViewById(R.id.tvsmoke_Sensor);
            tv_temp=itemView.findViewById(R.id.tvUltrasonicSensor);
            tv_date=itemView.findViewById(R.id.tv_temp);
            tv_time=itemView.findViewById(R.id.tv_humidity);



        }
    }
}
