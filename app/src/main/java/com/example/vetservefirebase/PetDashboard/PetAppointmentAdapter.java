package com.example.vetservefirebase.PetDashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vetservefirebase.Model.Appointment;
import com.example.vetservefirebase.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetAppointmentAdapter extends RecyclerView.Adapter<PetAppointmentAdapter.MyViewHolder> {

    private ArrayList<Appointment> appointments;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.providerName)
        TextView providerName;
        @BindView(R.id.appointmentDateTime)
        TextView appointmentDateTime;
        @BindView(R.id.appointmentServices)
        TextView appointmentServices;
        @BindView(R.id.appointmentStatus)
        TextView appointmentStatus;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public PetAppointmentAdapter(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAppointmentAdapter.MyViewHolder holder, int position) {

        holder.providerName.setText(appointments.get(position).getProviderKey());
        holder.appointmentDateTime.setText("Date and Time: " + appointments.get(position).getDate() + " - " + appointments.get(position).getTime());
        holder.appointmentServices.setText("Requested Services: ");
        holder.appointmentStatus.setText("Status: " + appointments.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

}
