package com.example.vetservefirebase.PetDashboard;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vetservefirebase.Model.Appointment;
import com.example.vetservefirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchedulesFragment extends Fragment {

    @BindView(R.id.petAppointmentList)
    RecyclerView petAppointmentList;
    @BindView(R.id.noappointments)
    TextView noappointments;
    private DatabaseReference appointmentRef = FirebaseDatabase.getInstance().getReference("appointments");
    private PetAppointmentAdapter mAdapter;
    ArrayList<Appointment> appointmentValues;
    private String uId;
    private String petKey;

    public SchedulesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_schedules, container, false);
        ButterKnife.bind(this, view);
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        appointmentValues = new ArrayList<>();
        mAdapter = new PetAppointmentAdapter(appointmentValues);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        petAppointmentList.setLayoutManager(mLayoutManager);
        petAppointmentList.setItemAnimator(new DefaultItemAnimator());
        petAppointmentList.setAdapter(mAdapter);
        petKey = ((PetDashboardFragment) getParentFragment()).setPetKey();
        getAppointments();
        return view;
    }

    private void getAppointments() {
        appointmentRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                   Appointment appointment = dataSnapshot.getValue(Appointment.class);
                   if(appointment.getuId().equals(uId) && appointment.getPetKey().equals(petKey) && appointment.getStatus().equals("pending")){
                       noappointments.setVisibility(View.INVISIBLE);
                       appointmentValues.add(appointment);
                       mAdapter.notifyDataSetChanged();
                   }
                }
                Log.d("atay", "onChildAdded: " + appointmentValues);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        appointmentValues.clear();
        mAdapter.notifyDataSetChanged();
    }


}
