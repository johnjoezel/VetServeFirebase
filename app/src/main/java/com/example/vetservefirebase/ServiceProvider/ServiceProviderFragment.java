package com.example.vetservefirebase.ServiceProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.vetservefirebase.Appointments.RequestAppointmentActivity;
import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.Model.UserProvider;
import com.example.vetservefirebase.Others.ShowAlert;
import com.example.vetservefirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceProviderFragment extends Fragment {

    private static final String TAG = "ServiceProviderFragment";
    @BindView(R.id.listofprovider)
    RecyclerView listofprovider;
    @BindView(R.id.searchProviders)
    Button searchProviders;
    @BindView(R.id.empty_view)
    LinearLayout empty_view;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("user_provider");
    private DatabaseReference providerRef = FirebaseDatabase.getInstance().getReference("providers");
    ArrayList<ServiceProvider> providers = new ArrayList<>();
    ArrayList<String> userprovKey = new ArrayList<>();
    MyRecyclerviewAdapter mAdapter;
    private String uId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_provider, container, false);
        ButterKnife.bind(this, view);
        listofprovider.setVisibility(View.INVISIBLE);
        empty_view.setVisibility(View.INVISIBLE);
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mAdapter = new MyRecyclerviewAdapter(providers);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listofprovider.setLayoutManager(mLayoutManager);
        listofprovider.setItemAnimator(new DefaultItemAnimator());
        listofprovider.setAdapter(mAdapter);
        getProviderKey();
        searchProviders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ServiceProvidersList.class);
                startActivity(intent);
            }
        });
        listofprovider.addOnItemTouchListener(new MyRecyclerTouchListener(getContext(), listofprovider, new MyRecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                dRef.child(uId).child(userprovKey.get(position)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserProvider userProvider = dataSnapshot.getValue(UserProvider.class);
                        Intent intent = new Intent(getContext(), RequestAppointmentActivity.class);
                        intent.putExtra("provider", providers.get(position));
                        intent.putExtra("providerKey", userProvider.getProviderKey());
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onLongClick(View view, int position) {
                ShowAlert.alertRemoveProvider(getContext(), providers.get(position).getFirstname(), userprovKey.get(position), uId);
            }
        }));
        return view;
    }


    private void getProviderKey(){
        dRef.child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    dRef.child(uId).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            UserProvider userProvider = dataSnapshot.getValue(UserProvider.class);
                            userprovKey.add(dataSnapshot.getKey());
                            String providerKey = userProvider.getProviderKey();
                            Log.d(TAG, "onChildAdded: " + providerKey);
                            providerRef.child(providerKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    ServiceProvider serviceProvider = dataSnapshot.getValue(ServiceProvider.class);
                                    providers.add(serviceProvider);
                                    mAdapter.notifyDataSetChanged();
                                    checkList();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                            String key = dataSnapshot.getKey();
                            for (int i = 0; i < userprovKey.size(); i++) {
                                if(userprovKey.get(i).equals(key)){
                                    userprovKey.remove(i);
                                    providers.remove(i);
                                    checkList();
                                    mAdapter.notifyDataSetChanged();
                                    return;
                                }
                            }
                            Log.d(TAG, "onChildRemoved: " + key);

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else
                    checkList();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void checkList() {
        if (providers.isEmpty()) {
            listofprovider.setVisibility(View.GONE);
            empty_view.setVisibility(View.VISIBLE);
        }
        else {
            listofprovider.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        if(!providers.isEmpty())
            mAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onStart() {
        if(!providers.isEmpty())
            mAdapter.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        userprovKey.clear();
        super.onDestroyView();
    }
}
