package com.example.vetservefirebase.Appointments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.R;
import com.example.vetservefirebase.ServiceProvider.ProviderProfileActivity;
import com.example.vetservefirebase.ServiceProvider.ProvidersListView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class SelectPetActivity extends AppCompatActivity {

    @BindView(R.id.listofpets)
    RecyclerView listofpets;
    private String uId;
    private DatabaseReference petRef = FirebaseDatabase.getInstance().getReference("pets");
    private FirebaseRecyclerAdapter mAdapter;
    ArrayList<Pet> pets = new ArrayList<Pet>();
    FirebaseRecyclerOptions<Pet> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pet);
        ButterKnife.bind(this);
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        initializeRecycler();
        listofpets.setAdapter(mAdapter);
        mAdapter.startListening();

    }

    private void initializeRecycler() {
        listofpets.setLayoutManager(new LinearLayoutManager(this));
        options = new FirebaseRecyclerOptions.Builder<Pet>()
                .setQuery(petRef.child(uId), Pet.class)
                .build();
        mAdapter = new FirebaseRecyclerAdapter<Pet, SelectPetActivity.RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RequestViewHolder requestViewHolder, int i, @NonNull Pet pet) {
                Glide.with(getApplicationContext()).load(pet.getPhotoUrl())
                        .transition(withCrossFade())
                        .thumbnail(0.5f)
                        .transform(new CircleTransform())
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(requestViewHolder.petImage);
                requestViewHolder.viewpetname.setText(pet.getPet_name());
                requestViewHolder.viewpetbreed.setText(pet.getBreed());
                requestViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("petKey", getRef(i).getKey());
                        returnIntent.putExtra("photoUrl", pet.getPhotoUrl());
                        returnIntent.putExtra("petname", pet.getPet_name());
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    }
                });
            }


            @NonNull
            @Override
            public SelectPetActivity.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_pet_layout, parent, false);
                SelectPetActivity.RequestViewHolder holder = new SelectPetActivity.RequestViewHolder(view);
                return holder;
            }
        };
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.petImage)
        ImageView petImage;
        @BindView(R.id.viewpetname)
        TextView viewpetname;
        @BindView(R.id.viewpetbreed)
        TextView viewpetbreed;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
