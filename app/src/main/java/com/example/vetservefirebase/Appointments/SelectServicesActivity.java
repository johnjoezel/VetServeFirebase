package com.example.vetservefirebase.Appointments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.Model.Services;
import com.example.vetservefirebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectServicesActivity extends AppCompatActivity {

    @BindView(R.id.services)
    RecyclerView services;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("services");
    private String providerKey;
    FirebaseRecyclerOptions<Services> options;
    ServiceProvider provider;
    ArrayList<Services> selectedServices = new ArrayList<>();
    FirebaseRecyclerAdapter<Services, SelectServicesActivity.RequestViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_services);
        ButterKnife.bind(this);
        if(getIntent().hasExtra("providerKey") && getIntent().hasExtra("provider")) {
            providerKey = getIntent().getStringExtra("providerKey");
            provider = getIntent().getParcelableExtra("provider");
        }
        loadServices();
        services.setAdapter(mAdapter);
        mAdapter.startListening();
    }

    @OnClick (R.id.submitServices) void toSubmit(){
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra("selectedServices", selectedServices);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    private void loadServices() {
        if(provider.getUsertype().equals("vetwithclinic")) {
            dRef = FirebaseDatabase.getInstance().getReference("clinics").child(providerKey).child("services");
        }else{
            dRef = FirebaseDatabase.getInstance().getReference("providers").child(providerKey).child("services");
        }
        services.setLayoutManager(new LinearLayoutManager(this));
        options = new FirebaseRecyclerOptions.Builder<Services>()
                .setQuery(dRef, Services.class)
                .build();
        mAdapter = new FirebaseRecyclerAdapter<Services, SelectServicesActivity.RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SelectServicesActivity.RequestViewHolder requestViewHolder, int i, @NonNull Services services) {
                requestViewHolder.servicename.setText(services.getServicename());
                requestViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                       if(b){
                           selectedServices.add(services);
                       }else{
                           selectedServices.remove(i);
                       }
                    }
                });
                requestViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        services.setChecked(!services.isChecked());
                        requestViewHolder.checkBox.setChecked(services.isChecked());
                    }
                });


            }



            @NonNull
            @Override
            public SelectServicesActivity.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_services, parent, false);
                SelectServicesActivity.RequestViewHolder holder = new SelectServicesActivity.RequestViewHolder(view);
                return holder;
            }
        };
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.servicename)
        TextView servicename;
        @BindView(R.id.checkBox)
        CheckBox checkBox;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }




}
