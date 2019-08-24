package com.example.vetservefirebase.ServiceProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceProviderActivity extends AppCompatActivity {

    @BindView(R.id.listofprovider)
    RecyclerView listofprovider;
    private DatabaseReference dRef;
    private DatabaseReference mDatabase  = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);
        setTitle("Providers");
        ButterKnife.bind(this);
        listofprovider.setLayoutManager(new LinearLayoutManager(this));
        dRef = FirebaseDatabase.getInstance().getReference("providers");
        FirebaseRecyclerOptions<ServiceProvider> options = new FirebaseRecyclerOptions.Builder<ServiceProvider>()
                .setQuery(dRef, ServiceProvider.class)
                .build();
        FirebaseRecyclerAdapter<ServiceProvider, RequestViewHolder> adapter = new FirebaseRecyclerAdapter<ServiceProvider, RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RequestViewHolder requestViewHolder, int i, @NonNull ServiceProvider provider) {
                requestViewHolder.clinicName.setText(provider.getClinicName());
                requestViewHolder.clinicAddress.setText(provider.getClinicAddress());
                requestViewHolder.clinicHours.setText(provider.getOpening() + " - " + provider.getClosing());
                requestViewHolder.clinicPhone.setText(provider.getClinicPhone());
                requestViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

            }
            @NonNull
            @Override
            public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_layout, parent, false);
                RequestViewHolder holder = new RequestViewHolder(view);
                return holder;
            }
        };
        listofprovider.setAdapter(adapter);
        adapter.startListening();
    }
    public static class RequestViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.clinicName)
        TextView clinicName;
        @BindView(R.id.clinicAddress)
        TextView clinicAddress;
        @BindView(R.id.clinicHours)
        TextView clinicHours;
        @BindView(R.id.clinicPhone)
        TextView clinicPhone;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
