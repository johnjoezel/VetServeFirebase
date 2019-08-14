package com.example.vetservefirebase.PetOwnerProfile;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
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
    ImageView profilepic;
    private int IMG_REQUEST= 1;
    private static String TAG = "User Profile";
    private String uId;
    private FirebaseUser user;
    private DatabaseReference dRef;
    private LayoutInflater inflater;
    private Uri photoPath;
    private Bitmap bitmap;
    String newfirstname, newmiddlename, newlastname, newcontact, newaddress;
    String oldfirstname, oldmiddlename, oldlastname, oldcontact, oldaddress;
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
        dRef = FirebaseDatabase.getInstance().getReference("users").child(uId);
        if(user != null){
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
                            .into(profilepic);
                    firstname.setText(user.getFirstname());
                    oldfirstname = firstname.getText().toString().trim();
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

    @OnClick ({R.id.profilepic, R.id.imageView2, R.id.imageView3, R.id.imageView4, R.id.imageView5,R.id.imageView6, R.id.btnUpdate})
    void toedit(View view){
        switch (view.getId()){
            case R.id.profilepic:
                selectimage();
                break;
            case R.id.imageView2:
                ShowAlert.showAlertwithreturn(inflater, getContext(), "Edit Firstname ",oldfirstname, firstname);
                break;
//            case R.id.imageView3:
//                oldmiddlename = middlename.getText().toString().trim();
//                newmiddlename = ShowAlert.showAlertwithreturn(inflater, getContext(), "Edit Middlename ",oldmiddlename, middlename);
//                Log.d(TAG, "toedit: "+ newmiddlename);
//                break;
//            case R.id.imageView4:
//                oldlastname = lastname.getText().toString().trim();
//                newlastname = ShowAlert.showAlertwithreturn(inflater, getContext(), "Edit Lastname ",oldlastname, lastname);
//                Log.d(TAG, "toedit: "+ newlastname);
//                break;
//            case R.id.imageView5:
//                oldcontact = contact.getText().toString().trim();
//                newcontact = ShowAlert.showAlertwithreturn(inflater, getContext(), "Edit Contact No: ",oldcontact, contact);
//                Log.d(TAG, "toedit: "+ newcontact);
//                break;
//            case R.id.imageView6:
//                oldaddress = address.getText().toString().trim();
//                newaddress = ShowAlert.showAlertwithreturn(inflater, getContext(), "Edit Address ",oldaddress, address);
//                Log.d(TAG, "toedit: "+ newaddress);
//                break;
            case R.id.btnUpdate:
                updatedata();
                break;
        }

    }



    private void selectimage() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null){
            photoPath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoPath);
                profilepic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void updatedata() {
            newfirstname = firstname.getText().toString().trim();
            if(!newfirstname.equals(oldfirstname))
                dRef.child("firstname").setValue(firstname.getText().toString().trim());
            else
                ShowAlert.showAlert(getContext(),"You haven't made any changes yet");
    }
}
