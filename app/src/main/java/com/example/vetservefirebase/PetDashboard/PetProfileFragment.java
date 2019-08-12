package com.example.vetservefirebase.PetDashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;



public class PetProfileFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.displaypetname)
    TextView displaypetname;
    @BindView(R.id.breedname)
    TextView displaybreed;
    @BindView(R.id.gender)
    TextView displaygender;
    @BindView(R.id.weight)
    TextView displayweight;
    @BindView(R.id.height)
    TextView displayheight;
    @BindView(R.id.petPic)
    ImageView petPic;
    private String petname, breedname, gender,weight, height;
    private Bitmap bitmap;
    private int IMG_REQUEST= 1;
    private String uId;
    private String petKey;
    private DatabaseReference dRef;
    private Bundle arguments;

    public PetProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pet_profile_fragment, container, false);
        ButterKnife.bind(this, view);
        arguments = getArguments();
        uId = arguments.getString("uId");
        petKey = arguments.getString("petKey");
        dRef = FirebaseDatabase.getInstance().getReference("pets").child(uId).child(petKey);
        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pet pet = dataSnapshot.getValue(Pet.class);
                displaypetname.setText(petname = pet.getPet_name());
                displaybreed.setText(breedname = pet.getBreed());
                displaygender.setText(gender = pet.getGender());
                if(pet.getSpecies().equals("Dog")) {
                    petPic.setImageResource(R.drawable.dogpic);
                }else if (pet.getSpecies().equals("Cat")){
                    petPic.setImageResource(R.drawable.catpic);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgProfpic:
                selectImage();
                break;
        }
    }

    public void selectImage(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }
}

