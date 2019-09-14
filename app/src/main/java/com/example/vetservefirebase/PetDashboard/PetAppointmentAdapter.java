package com.example.vetservefirebase.PetDashboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vetservefirebase.Model.Appointment;
import com.example.vetservefirebase.Model.Clinic;
import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.Model.Services;
import com.example.vetservefirebase.R;
import com.example.vetservefirebase.ServiceProvider.MyRecyclerTouchListener;
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

    private ArrayList<Appointment> appointmentValues;

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

    public PetAppointmentAdapter(ArrayList<Appointment> appointmentValues) {
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
        String providerKey = appointmentValues.get(position).getProviderKey();
        DatabaseReference provRef = FirebaseDatabase.getInstance().getReference("providers");
        provRef.child(providerKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    ServiceProvider serviceProvider = dataSnapshot.getValue(ServiceProvider.class);
                    if(serviceProvider.getUsertype().equals("vetwithclinic")){
                        DatabaseReference clinicRef = FirebaseDatabase.getInstance().getReference("clinics").child(providerKey);
                        clinicRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Clinic clinic = dataSnapshot.getValue(Clinic.class);
                                holder.providerName.setText(clinic.getName());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }else {
                        holder.providerName.setText("Dr. " + serviceProvider.getFirstname() + " " + serviceProvider.getLastname());

                    }
                    holder.appointmentDateTime.setText("Date and Time: " + appointmentValues.get(position).getDate() + " - " + appointmentValues.get(position).getTime());
                    holder.appointmentServices.setText("Requested Services: " + appointmentValues.get(position).getServices_requested());
                    holder.appointmentStatus.setText("Status: " + appointmentValues.get(position).getStatus());
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
