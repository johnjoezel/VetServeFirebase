package com.example.vetservefirebase.PetOwner;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.vetservefirebase.MainActivity;
import com.example.vetservefirebase.Model.User;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.Others.ShowAlert;
import com.example.vetservefirebase.Others.Utils;
import com.example.vetservefirebase.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class EditProfileFragment extends Fragment {

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
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private LayoutInflater inflater;
    private Uri photoPath;
    private Bitmap bitmap;
    String newfirstname, newmiddlename, newlastname, newcontact, newaddress, newphotoUrl;
    String oldfirstname, oldmiddlename, oldlastname, oldcontact, oldaddress, oldphotoUrl;
    private ProgressDialog progressDialog;
    Bundle userarguments;
    User currentuser;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        this.inflater = inflater;
        ButterKnife.bind(this, view);
        userarguments = getArguments();
        currentuser = userarguments.getParcelable("currentuser");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uId = user.getUid();
        dRef = FirebaseDatabase.getInstance().getReference("users").child(uId);
        Glide.with(getActivity()).load(currentuser.getPhotoUrl())
                .transition(withCrossFade())
                .thumbnail(0.5f)
                .transform(new CircleTransform())
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(profilepic);
//        oldphotoUrl = user.getPhotoUrl();
        firstname.setText(currentuser.getFirstname());
//        oldfirstname = firstname.getText().toString().trim();
        middlename.setText(currentuser.getMiddlename());
//        oldmiddlename = middlename.getText().toString().trim();
        lastname.setText(currentuser.getLastname());
//        oldlastname = lastname.getText().toString().trim();
        contact.setText(currentuser.getContact());
//        oldcontact = contact.getText().toString().trim();
        address.setText(currentuser.getAddress());
//        oldaddress = address.getText().toString().trim();

        return view;
    }

    @OnClick ({R.id.profilepic, R.id.imageView2, R.id.imageView3, R.id.imageView4, R.id.imageView5,R.id.imageView6, R.id.btnUpdate})
    void toedit(View view){
        switch (view.getId()){
            case R.id.profilepic:
                selectimage();
                break;
            case R.id.imageView2:
                ShowAlert.showAlertwithreturn(inflater, getContext(), "Edit Firstname ",currentuser.getFirstname(), firstname);
                break;
            case R.id.imageView3:
                ShowAlert.showAlertwithreturn(inflater, getContext(), "Edit Middlename ",currentuser.getMiddlename(), middlename);
                Log.d(TAG, "toedit: "+ newmiddlename);
                break;
            case R.id.imageView4:
                ShowAlert.showAlertwithreturn(inflater, getContext(), "Edit Lastname ",currentuser.getLastname(), lastname);
                Log.d(TAG, "toedit: "+ newlastname);
                break;
            case R.id.imageView5:
                ShowAlert.showAlertwithreturn(inflater, getContext(), "Edit Contact No: ",currentuser.getContact(), contact);
                Log.d(TAG, "toedit: "+ newcontact);
                break;
            case R.id.imageView6:
                ShowAlert.showAlertwithreturn(inflater, getContext(), "Edit Address ",currentuser.getAddress(), address);
                Log.d(TAG, "toedit: "+ newaddress);
                break;
            case R.id.btnUpdate:
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Updating Profile");
                progressDialog.show();
                if(photoPath != null)
                    getImageUrl();
                else
                    updatedata();
                break;
        }

    }

    private void getImageUrl() {
        String path = "ProfileImage/" + UUID.randomUUID() + ".jpg";
        StorageReference profileImageRef = storage.getReference(path);
        profileImageRef.putFile(photoPath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return profileImageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    String photoUrl = task.getResult().toString();
                    Log.d(TAG, "onComplete: " + photoUrl);
                    setPhotoUrl(photoUrl);
                    updatedata();
                }
            }
        });
    }


    private void selectimage() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    public void setPhotoUrl(String photoUrl){
        this.newphotoUrl = photoUrl;
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
        newmiddlename = middlename.getText().toString().trim();
        newlastname = lastname.getText().toString().trim();
        newcontact = contact.getText().toString().trim();
        newaddress = address.getText().toString().trim();
        if(newphotoUrl != null){
            dRef.child("photoUrl").setValue(newphotoUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful())
                        updateSuccessful();
                }
            });
        }
        if(!newfirstname.equals(currentuser.getFirstname()))
                dRef.child("firstname").setValue(newfirstname);
        if(!newmiddlename.equals(currentuser.getMiddlename()))
            dRef.child("middlename").setValue(newmiddlename);
        if(!newlastname.equals(currentuser.getLastname()))
            dRef.child("lastname").setValue(newlastname);
        if(!newcontact.equals(currentuser.getContact()))
            dRef.child("contact").setValue(newcontact);
        if(!newaddress.equals(currentuser.getAddress()))
            dRef.child("address").setValue(newaddress);
        updateSuccessful();
    }
    void updateSuccessful(){
        progressDialog.dismiss();
        if(newfirstname.equals(currentuser.getFirstname()) && newmiddlename.equals(currentuser.getMiddlename())
                && newlastname.equals(currentuser.getLastname()) && newcontact.equals(currentuser.getContact()) &&
                newaddress.equals(currentuser.getAddress()) && newphotoUrl == null){
            ShowAlert.showAlert(getContext(),"You haven't made any changes yet");
            progressDialog.dismiss();
        }else{
            ((MainActivity)getActivity()).loadNavHeader(newphotoUrl, user.getEmail());
            Utils.showMessage(getContext(), "Profile Updated");
            Utils.setIntent(getContext(), MainActivity.class);
        }
    }
}
