package com.example.vetservefirebase.PetDashboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vetservefirebase.Model.Appointment;
import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.Model.Services;
import com.example.vetservefirebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetAppointmentAdapter extends RecyclerView.Adapter<PetAppointmentAdapter.MyViewHolder> {

    private ArrayList<Map<String, Object>> appointmentValues;

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

    public PetAppointmentAdapter(ArrayList<Map<String, Object>> appointmentValues) {
        this.appointmentValues = appointmentValues;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAppointmentAdapter.MyViewHolder holder, int position) {
        StringBuilder selectedServices = new StringBuilder();
        String providerKey = appointmentValues.get(position).get("providerKey").toString();
        DatabaseReference provRef = FirebaseDatabase.getInstance().getReference("providers");
        ArrayList<Services> services = (ArrayList<Services>) appointmentValues.get(position).get("services_requested");
        for (int i = 0; i < services.size(); i++) {

            selectedServices.append(services.get(i).getServicename() + ", ");
        }

        provRef.child(providerKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    ServiceProvider serviceProvider = dataSnapshot.getValue(ServiceProvider.class);
                    holder.providerName.setText(serviceProvider.getClinicname());
                    holder.appointmentDateTime.setText("Date and Time: " + appointmentValues.get(position).get("date") + " - " + appointmentValues.get(position).get("time"));
                    holder.appointmentServices.setText("Requested Services: " +  selectedServices);
//                    holder.appointmentStatus.setText("Status: " + appointments.get(position).getStatus());
                }
//                services.clear();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return appointmentValues.size();
    }

}
