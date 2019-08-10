package com.example.vetservefirebase.SignUp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.vetservefirebase.Model.User;
import com.example.vetservefirebase.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class SignUpPresenterImpl implements SignUpPresenter {

    private Context context;
    private SignUpView signUpView ;
    private FirebaseAuth mAuth ;
    private String errorcode;
    private String errmessage;
    private ProgressDialog progressDialog;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private DatabaseReference mDatabase  = FirebaseDatabase.getInstance().getReference();
    private Uri photopath;

    public SignUpPresenterImpl(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    @Override
    public void signUp(String email, String password, ArrayList<String> data, Context context, Uri photopath) {
        this.context = context;
        this.photopath = photopath;
        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
        signUpView.setProgressVisibility(true);
        Log.d("dataentry", data.toString());
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener((Activity) signUpView, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    //Dismiss progress dialog
                    progressDialog.dismiss();
                    signUpView.setProgressVisibility(false);
                    if (!task.isSuccessful()) {
                        try {
                            throw task.getException();
                        } catch(FirebaseAuthWeakPasswordException e){
                            signUpView.signUpError(e.getErrorCode(), e.getReason());
                        }catch (FirebaseAuthEmailException e) {
                            Log.d("code", e.getErrorCode());
                            signUpView.signUpError("default", e.getMessage());
                        }catch (Exception e) {
                            signUpView.signUpError("default", e.getMessage());
                        }
                    } else {
                        FirebaseUser user = mAuth.getInstance().getCurrentUser();
                        if(user != null){
                            String uId = user.getUid();
                            uploadUserProfile(uId, data);
                        }else{
                            Toast.makeText(context, "Problem creating account", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
    }


    @Override
    public boolean validation(Bitmap bitmap, String email, String displayname, String password, String cpassword) {
        Boolean valid;
        if (TextUtils.isEmpty(email)  || TextUtils.isEmpty(displayname) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cpassword)) {
            if(TextUtils.isEmpty(email)) {
                errorcode = "ee";
            }else if(TextUtils.isEmpty(displayname)){
                errorcode = "en";
            }else if (TextUtils.isEmpty(password)){
                errorcode = "ep";
            }else{
                errorcode = "ecp";
            }
            errmessage = "Field is required";
            signUpView.signUpError(errorcode, errmessage);
            valid =  false;
        }else if(!password.equals(cpassword)){
            signUpView.signUpError("pm", "Password Mismatch");
            valid = false;
        }else if(bitmap == null){
            signUpView.signUpError("np", "Please provide a photo");
            valid = false;
        } else valid = true;
        Log.d("truth", valid.toString());
        return valid;
    }


    @Override
    public void attachView(SignUpView view) {
        signUpView = view ;
    }

    @Override
    public void detachView() {
        signUpView = null ;
    }

    public void uploadUserProfile(final String uId, ArrayList<String> data){
        final String firstname = data.get(0);
        final String middlename = data.get(1);
        final String lastname = data.get(2);
        final String bday = data.get(3);
        final String contact = data.get(4);
        final String displayname = data.get(5);
        String path = "ProfileImage/" + UUID.randomUUID() + ".jpg";
        StorageReference profileImageRef = storage.getReference(path);
        profileImageRef.putFile(photopath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
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
                        Log.d("unsani?", photoUrl);
                        writeUserwithProfileImage(uId, firstname, middlename, lastname, bday, contact, displayname, photoUrl);
                    }
                }
            });
    }
    private void writeUserwithProfileImage(String uId, String firstname, String middlename, String lastname, String bday, String contact, String displayname, String photoUrl){
        User user = new User(firstname, middlename, lastname, bday, contact, displayname, photoUrl);
        //Add user to firebase database
        DatabaseReference userRef = mDatabase.child("users");
        userRef.child(uId).setValue(user);
        signUpView.signUpSuccess();
    }
}
