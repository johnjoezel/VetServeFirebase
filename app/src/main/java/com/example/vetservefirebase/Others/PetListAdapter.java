package com.example.vetservefirebase.Others;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.PetListViewHolder> {
    private final Context context;
    private List<Pet> mPetList;
    public static class PetListViewHolder extends RecyclerView.ViewHolder{
        public ImageView petPicture;
        @BindView(R.id.viewpetname)
        TextView petname;
        @BindView(R.id.viewpetbreed)
        TextView petbreed;

        public PetListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public PetListAdapter(Context context, List<Pet> petList) {
        this.context = context;
        mPetList = petList;
    }

    @NonNull
    @Override
    public PetListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_layout, parent, false);
        PetListViewHolder evh = new PetListViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull PetListViewHolder holder, int position) {
        Pet currentItem = mPetList.get(position);

//        holder.petPicture(currentItem.getImageResource());\
        holder.petname.setText(currentItem.getPet_name());
        holder.petbreed.setText(currentItem.getBreed());
    }

    @Override
    public int getItemCount() {
        return mPetList.size();
    }
}
