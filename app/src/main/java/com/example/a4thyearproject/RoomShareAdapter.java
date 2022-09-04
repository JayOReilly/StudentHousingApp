package com.example.a4thyearproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class RoomShareAdapter extends RecyclerView.Adapter<RoomShareAdapter.ViewHolder> {



    private final Context mContext;
    private final ArrayList<RoomShareModel > rms1;

    public RoomShareAdapter(Context mContext, ArrayList<RoomShareModel> rms1) {
        this.mContext = mContext;
        this.rms1 = rms1;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rent,parent,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RoomShareModel  roomModel = rms1.get(position);
        //TextViews
        holder.text1.setText("Type Of House: " + roomModel.getType());
        holder.text2.setText("Location Of House: " + roomModel.getLocation());
        holder.text3.setText("Price Of House: " + roomModel.getPrice());
        holder.text4.setText("Details of Property: " + roomModel.getDetails());

        String imageUri=null;
        imageUri= roomModel.getImage();
        Picasso.get().load(imageUri).into(holder.imgV);






    }

    @Override
    public int getItemCount() {
        return rms1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgV;
        TextView text1,text2,text3,text4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV = itemView.findViewById(R.id.img1);
            text3 = itemView.findViewById(R.id.priceHouse);

            text2 = itemView.findViewById(R.id.locationHouse);
            text1 = itemView.findViewById(R.id.typeHouse);
            text4 = itemView.findViewById(R.id.detailsHouse);

        }
    }
}

