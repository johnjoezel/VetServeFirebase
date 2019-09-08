package com.example.vetservefirebase.Appointments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vetservefirebase.Model.Appointment;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class SelectPetAdapter extends RecyclerView.Adapter<SelectPetAdapter.MyViewHolder> {

    private final Context context;
    private ArrayList<Pet> pets;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.petImage)
        ImageView petImage;
        @BindView(R.id.viewpetname)
        TextView viewpetname;
        @BindView(R.id.viewpetbreed)
        TextView viewpetbreed;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public SelectPetAdapter(Context context, ArrayList<Pet> pets) {
        this.context = context;
        this.pets = pets;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_pet_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectPetAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load(pets.get(position).getPhotoUrl())
                .transition(withCrossFade())
                .thumbnail(0.5f)
                .transform(new CircleTransform())
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.petImage);
        holder.viewpetname.setText(pets.get(position).getPet_name());
        holder.viewpetbreed.setText(pets.get(position).getBreed());
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

}
