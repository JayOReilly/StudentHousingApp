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

public class DigsAdapter extends RecyclerView.Adapter<DigsAdapter.ViewHolder> {



    private final Context jContext;
    private final ArrayList<DigsModel> dm;

    public DigsAdapter(Context mContext, ArrayList<DigsModel> dm) {
        this.jContext = mContext;
        this.dm = dm;
    }



    @NonNull
    @Override
    public DigsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_digs_dash,parent,false);

        return new DigsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DigsAdapter.ViewHolder holder, int position) {

        DigsModel rentmodel = dm.get(position);
        //TextViews
        holder.text1.setText("Type Of House: " + rentmodel.getType());
        holder.text2.setText("Location Of House: " + rentmodel.getLocation());
        holder.text3.setText("Price Of House: " + rentmodel.getPrice());
        holder.text4.setText("Details of Property: " + rentmodel.getDetails());


        String imageUri=null;
        imageUri= rentmodel.getImage();
        Picasso.get().load(imageUri).into(holder.imgV);






    }

    @Override
    public int getItemCount() {
        return dm.size();
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
            text4 = itemView.findViewById(R.id.detailsDigs);

        }
    }

}