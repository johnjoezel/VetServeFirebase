package com.example.vetservefirebase.PetListView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vetservefirebase.AddPet.AddPetActivity;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.PetDashboard.PetDashboardActivity;
import com.example.vetservefirebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PetListViewActivity extends AppCompatActivity {
    String uId;
    @BindView(R.id.listofpet)
    RecyclerView listofpets;
    private DatabaseReference dRef, userpetref;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list_view);
        setTitle("My Pets");
        ButterKnife.bind(this);
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dRef = FirebaseDatabase.getInstance().getReference("pets");
        userpetref = FirebaseDatabase.getInstance().getReference("pets").child(uId);
        listofpets.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Pet> options = new FirebaseRecyclerOptions.Builder<Pet>()
                .setQuery(dRef.child(uId), Pet.class)
                .build();
        FirebaseRecyclerAdapter<Pet, RequestViewHolder> adapter = new FirebaseRecyclerAdapter<Pet, RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RequestViewHolder requestViewHolder, int i, @NonNull Pet pet) {
                requestViewHolder.petname.setText(pet.getPet_name());
                requestViewHolder.petbreed.setText(pet.getBreed());
                requestViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String petKey = getRef(i).getKey();
                        Log.d("walalagi?", "onClick: " + petKey + uId);
                        Intent intent = new Intent(getApplicationContext(), PetDashboardActivity.class);
                        intent.putExtra("petKey", petKey);
                        intent.putExtra("uId", uId);
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_layout, parent, false);
                RequestViewHolder holder = new RequestViewHolder(view);
                return holder;
            }
        };
        listofpets.setAdapter(adapter);
        adapter.startListening();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.viewpetname)
        TextView petname;
        @BindView(R.id.viewpetbreed)
        TextView petbreed;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, AddPetActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}