package com.example.vetservefirebase.PetOwnerProfile;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vetservefirebase.Model.User;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.Others.ShowAlert;
import com.example.vetservefirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ProfileFragment extends Fragment {

    @BindView(R.id.firstname)
    TextView firstname;
    @BindView(R.id.middlename)
    TextView middlename;
    @BindView(R.id.lastname)
    TextView lastname;
    @BindView(R.id.contact)
    TextView contact;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.profilepic)
    ImageView profilepifc;
    private static String TAG = "User Profile";
    private String uId;
    private FirebaseUser user;
    private DatabaseReference dRef;
    private LayoutInflater inflater;

    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        this.inflater = inflater;
        ButterKnife.bind(this, view);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uId = user.getUid();
        if(user != null){
            dRef = FirebaseDatabase.getInstance().getReference("users").child(uId);
            dRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    Glide.with(getActivity()).load(user.getPhotoUrl())
                            .transition(withCrossFade())
                            .thumbnail(0.5f)
                            .transform(new CircleTransform())
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(profilepifc);
                    firstname.setText(user.getFirstname());
                    middlename.setText(user.getMiddlename());
                    lastname.setText(user.getLastname());
                    contact.setText(user.getContact());
                    address.setText(user.getAddress());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        return view;
    }

    @OnClick ({R.id.imageView2, R.id.imageView3, R.id.imageView4, R.id.imageView5,R.id.imageView6})
    void toedit(View view){
        switch (view.getId()){
            case R.id.imageView2:
                String newfirstname = ShowAlert.showAlertwithreturn(inflater, getContext(), "Firstname ",firstname.getText().toString().trim());
        }

    }
}
