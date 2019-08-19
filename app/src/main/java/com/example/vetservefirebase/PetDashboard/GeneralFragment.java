package com.example.vetservefirebase.PetDashboard;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeneralFragment extends Fragment {

    private static final String TAG = "General Fragment";
    @BindView(R.id.breedname)
    TextView displaybreed;
    @BindView(R.id.gender)
    TextView displaygender;
    @BindView(R.id.weight)
    TextView displayweight;
    @BindView(R.id.height)
    TextView displayheight;
    String petname, breedname, gender,weight, height;
    private OnFragmentInteractionListener mListener;
    Bundle arguments = new Bundle();
    String petKey;
    String uId;
    private DatabaseReference dRef;

    public GeneralFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_general, container, false);
        ButterKnife.bind(this, view);
        arguments = getArguments();
        uId = ((PetDashboardActivity)this.getActivity()).passUid();
        petKey = arguments.getString("petKey");
        dRef = FirebaseDatabase.getInstance().getReference("pets").child(uId).child(petKey);
        setInformation();
        return view;

    }

    private void setInformation() {
        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Pet pet = dataSnapshot.getValue(Pet.class);
                    displaybreed.setText(breedname = pet.getBreed());
                    displaygender.setText(gender = pet.getGender());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
