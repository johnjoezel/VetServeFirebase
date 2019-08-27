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

import com.example.vetservefirebase.MainActivity;
import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceProvidersList extends AppCompatActivity {

    private DatabaseReference dRef;
    @BindView(R.id.searchProviders)
    RecyclerView searchProviders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_providers_list);
        ButterKnife.bind(this);
        searchProviders.setLayoutManager(new LinearLayoutManager(this));
        dRef = FirebaseDatabase.getInstance().getReference("providers");
        FirebaseRecyclerOptions<ServiceProvider> options = new FirebaseRecyclerOptions.Builder<ServiceProvider>()
                .setQuery(dRef, ServiceProvider.class)
                .build();
        FirebaseRecyclerAdapter<ServiceProvider, ServiceProvidersList.RequestViewHolder> adapter = new FirebaseRecyclerAdapter<ServiceProvider, ServiceProvidersList.RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ServiceProvidersList.RequestViewHolder requestViewHolder, int i, @NonNull ServiceProvider provider) {
                requestViewHolder.viewClinicname.setText(provider.getClinicname());
                requestViewHolder.viewCliniclocation.setText(provider.getLocation());
                requestViewHolder.viewClinicphone.setText(provider.getPhonenumber());
                requestViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

            }
            @NonNull
            @Override
            public ServiceProvidersList.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.providers_item, parent, false);
                ServiceProvidersList.RequestViewHolder holder = new ServiceProvidersList.RequestViewHolder(view);
                return holder;
            }
        };
        searchProviders.setAdapter(adapter);
        adapter.startListening();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.viewClinicname)
        TextView viewClinicname;
        @BindView(R.id.viewCliniclocation)
        TextView viewCliniclocation;
        @BindView(R.id.viewClinicphone)
        TextView viewClinicphone;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
