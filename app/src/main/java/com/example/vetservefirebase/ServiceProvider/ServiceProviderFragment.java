package com.example.vetservefirebase.ServiceProvider;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vetservefirebase.AddPet.AddPetActivity;
import com.example.vetservefirebase.LocateClinic;
import com.example.vetservefirebase.MainActivity;
import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceProviderFragment extends Fragment {

    @BindView(R.id.listofprovider)
    RecyclerView listofprovider;
    private DatabaseReference dRef;
    ArrayList<String> petproviders = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        dRef = FirebaseDatabase.getInstance().getReference("providers");
        if(!petproviders.isEmpty()) {
            view = inflater.inflate(R.layout.fragment_service_provider, container, false);
            listofprovider.setLayoutManager(new LinearLayoutManager((MainActivity) getActivity()));
            ButterKnife.bind(this, view);

            FirebaseRecyclerOptions<ServiceProvider> options = new FirebaseRecyclerOptions.Builder<ServiceProvider>()
                    .setQuery(dRef, ServiceProvider.class)
                    .build();
            FirebaseRecyclerAdapter<ServiceProvider, RequestViewHolder> adapter = new FirebaseRecyclerAdapter<ServiceProvider, RequestViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull RequestViewHolder requestViewHolder, int i, @NonNull ServiceProvider provider) {
                    requestViewHolder.clinicName.setText(provider.getClinicname());
                    requestViewHolder.clinicAddress.setText(provider.getLocation());
                    requestViewHolder.clinicPhone.setText(provider.getPhonenumber());
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
        }else{
            view = inflater.inflate(R.layout.no_providers, container, false);
            Button searchProviders = view.findViewById(R.id.searchProviders);
            searchProviders.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ServiceProvidersList.class);
                    startActivity(intent);
                }
            });
        }
        return view;
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
