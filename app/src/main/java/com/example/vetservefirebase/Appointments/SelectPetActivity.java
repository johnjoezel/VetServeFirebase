package com.example.vetservefirebase.Appointments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.Model.UserProvider;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.Others.ShowAlert;
import com.example.vetservefirebase.Appointments.SelectPetAdapter;
import com.example.vetservefirebase.R;
import com.example.vetservefirebase.ServiceProvider.MyRecyclerTouchListener;
import com.example.vetservefirebase.ServiceProvider.MyRecyclerviewAdapter;
import com.example.vetservefirebase.ServiceProvider.ProviderProfileActivity;
import com.example.vetservefirebase.ServiceProvider.ProvidersListView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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


public class SelectPetActivity extends AppCompatActivity {

    @BindView(R.id.listofpets)
    RecyclerView listofpets;
    private String uId;
    private DatabaseReference petRef = FirebaseDatabase.getInstance().getReference("pets");
    private SelectPetAdapter mAdapter;
    ArrayList<Pet> pets = new ArrayList<Pet>();
    ArrayList<String> petKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pet);
        ButterKnife.bind(this);
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mAdapter = new SelectPetAdapter(this,pets);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        listofpets.setLayoutManager(mLayoutManager);
        listofpets.setItemAnimator(new DefaultItemAnimator());
        listofpets.setAdapter(mAdapter);
        listofpets.addOnItemTouchListener(new MyRecyclerTouchListener(this, listofpets, new MyRecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("petKey", petKeys.get(position));
                returnIntent.putExtra("photoUrl", pets.get(position).getPhotoUrl());
                returnIntent.putExtra("petname", pets.get(position).getPet_name());
                setResult(RESULT_OK, returnIntent);
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        getPets();
    }

    private void getPets() {
        petRef.child(uId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        Pet pet = ds.getValue(Pet.class);
                        if (!pet.getStatus().equals("removed")) {
                            petKeys.add(ds.getKey());
                            pets.add(pet);
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
