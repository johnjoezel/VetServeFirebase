package com.example.vetservefirebase.ServiceProvider;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.MyViewHolder> {

    private ArrayList<ServiceProvider> providers;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.clinicName)
        TextView clinicName;
        @BindView(R.id.reqAppointment)
        TextView reqAppointment;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public MyRecyclerviewAdapter(ArrayList<ServiceProvider> providers) {
        this.providers = providers;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerviewAdapter.MyViewHolder holder, int position) {
        holder.clinicName.setText(providers.get(position).getFirstname());
    }

    @Override
    public int getItemCount() {
        return providers.size();
    }

}
