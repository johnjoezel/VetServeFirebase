package com.example.vetservefirebase.AddPet;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.PetDashboard.PetDashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddPetPresenterImpl implements AddPetPresenter {

    public AddPetView addPetView ;
    private DatabaseReference mDatabase  = FirebaseDatabase.getInstance().getReference();
    private ProgressDialog progressDialog;
    String status = "active";


    public AddPetPresenterImpl() {

    }

    @Override
    public void attachView(AddPetView view) {
        addPetView = view;
    }

    @Override
    public void detachView() {
        addPetView = null;
    }

    @Override
    public void addpet(Context context, String uId, String petname, String petspecies, String petbreed, String petgender, String petdob, String petcolor, String photoUrl) {
        Pet pet = new Pet(petname, petspecies, petbreed, petgender, petdob, petcolor, photoUrl, status);
        //Add user to firebase database
        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Adding Pet");
        progressDialog.show();
        DatabaseReference userRef = mDatabase.child("pets");
        String id = userRef.push().getKey();
        userRef.child(uId).child(id).setValue(pet).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if(task.isSuccessful())
                    addPetView.addPetSuccess();
            }
        });
    }

    @Override
    public void updatepet(Context context, String petKey, String uId, String petname, String petspecies, String petbreed, String petgender, String petdob, String petcolor, String photoUrl) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Adding Pet");
        progressDialog.show();
        DatabaseReference dRef = mDatabase.child("pets").child(uId);
        Map<String, Object> map = new HashMap();
        map.put("pet_name", petname);
        map.put("species", petspecies);
        map.put("breed", petbreed);
        map.put("gender", petgender);
        map.put("dob", petdob);
        map.put("color", petcolor);
        map.put("photoUrl",photoUrl);
        map.put("status", status);
        Log.d("children", "updatepet: " + map);
        dRef.child(petKey).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(context, "Pet Information Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, PetDashboardActivity.class);
                    intent.putExtra("petKey", petKey);
                    context.startActivity(intent);
                }
            }
        });
    }


}
