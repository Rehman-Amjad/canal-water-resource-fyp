package com.example.safesocietyalertsystem.Model;


import android.content.Context;
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

import java.io.ByteArrayOutputStream;
import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.MySensorViewHolder>
{
    private Context context;
    private List<User> mDatalist;

    public SensorAdapter(Context context, List<User> mDatalist) {
        this.context = context;
        this.mDatalist = mDatalist;
    }

    @NonNull
    @Override
    public MySensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(context).inflate(R.layout.sesnor_list,parent,false);



        return new MySensorViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull MySensorViewHolder holder, int position) {

//        User user=mDatalist.get(position);
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] imageBytes = baos.toByteArray();
//        imageBytes = Base64.decode(user.getImg(), Base64.DEFAULT);
//        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//
//        holder.imageView.setImageBitmap(decodedImage);
//        holder.tvFire_Sensor.setText("Fire Sensor: "+user.getFireSensor());
//

    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public class MySensorViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvFire_Sensor;

        public MySensorViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.card_imageShow);
            tvFire_Sensor=itemView.findViewById(R.id.tvShow_Sensor);
        }
    }
}





