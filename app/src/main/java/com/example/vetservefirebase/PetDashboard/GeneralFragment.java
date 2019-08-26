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
import com.google.firebase.auth.FirebaseAuth;
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
    Pet pet;
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
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        pet = arguments.getParcelable("pet");
        setInformation();
        return view;

    }

    private void setInformation() {
        displaybreed.setText(breedname = pet.getBreed());
        displaygender.setText(gender = pet.getGender());
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
