package com.example.vetservefirebase.ServiceProvider;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vetservefirebase.Model.Clinic;
import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProvidersListView extends Fragment {


    @BindView(R.id.searchProviders)
    RecyclerView searchProviders;
    private DatabaseReference dRef;
    FirebaseRecyclerAdapter<ServiceProvider, ProvidersListView.RequestViewHolder> adapter;
    public ProvidersListView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_providers_list_view, container, false);
        ButterKnife.bind(this, view);
        searchProviders.setLayoutManager(new LinearLayoutManager(getActivity()));
        dRef = FirebaseDatabase.getInstance().getReference("providers");
        FirebaseRecyclerOptions<ServiceProvider> options = new FirebaseRecyclerOptions.Builder<ServiceProvider>()
                .setQuery(dRef, ServiceProvider.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<ServiceProvider, ProvidersListView.RequestViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull ProvidersListView.RequestViewHolder requestViewHolder, int i, @NonNull ServiceProvider provider) {
                Log.d("pisti", "onBindViewHolder: " + provider.getSubscribed());
                if(!provider.getSubscribed().equals("pending")){
                    if(provider.getUsertype().equals("vetwithclinic")){
                        String key = adapter.getRef(i).getKey();
                        dRef = FirebaseDatabase.getInstance().getReference("clinics").child(key);
                        dRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Clinic clinic = dataSnapshot.getValue(Clinic.class);
                                requestViewHolder.viewClinicname.setText(clinic.getName());
                                requestViewHolder.viewCliniclocation.setText(clinic.getLocation());

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }else{
                        requestViewHolder.viewClinicname.setText("Dr. "+ provider.getFirstname() + provider.getLastname());
                        requestViewHolder.viewCliniclocation.setText(provider.getAddress());
                    }
                    requestViewHolder.linkToClinicProfile.setPaintFlags(requestViewHolder.linkToClinicProfile.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    requestViewHolder.linkToClinicProfile.setText("View Details");
                    requestViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String providerKey = adapter.getRef(searchProviders.getChildLayoutPosition(view)).getKey();
                            Intent intent = new Intent(getContext(), ProviderProfileActivity.class);
                            intent.putExtra("provider", provider);
                            intent.putExtra("providerKey", providerKey);
                            startActivity(intent);
                        }
                    });
                }




//                requestViewHolder.viewClinicname.setText(provider.getClinicname());
//                requestViewHolder.viewCliniclocation.setText(provider.getLocation());
//                requestViewHolder.linkToClinicProfile.setPaintFlags(requestViewHolder.linkToClinicProfile.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//                requestViewHolder.linkToClinicProfile.setText("View Details");
//                requestViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String providerKey = adapter.getRef(searchProviders.getChildLayoutPosition(view)).getKey();
//                        Intent intent = new Intent(getContext(), ProviderProfileActivity.class);
//                        intent.putExtra("provider", provider);
//                        intent.putExtra("providerKey", providerKey);
//                        startActivity(intent);
//                    }
//                });

            }
            @NonNull
            @Override
            public ProvidersListView.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.providers_item, parent, false);
                ProvidersListView.RequestViewHolder holder = new ProvidersListView.RequestViewHolder(view);
                return holder;
            }
        };
        searchProviders.setAdapter(adapter);
        adapter.startListening();
        return view;
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.viewClinicname)
        TextView viewClinicname;
        @BindView(R.id.viewCliniclocation)
        TextView viewCliniclocation;
        @BindView(R.id.linkToClinicProfile)
        TextView linkToClinicProfile;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.mapview, menu);
        return;
    }

}
