package com.example.vetservefirebase.AddPet;


import android.app.ProgressDialog;
import android.content.Context;
import androidx.annotation.NonNull;
import com.example.vetservefirebase.Model.Pet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPetPresenterImpl implements AddPetPresenter {

    public AddPetView addPetView ;
    private String uId;
    private String petname;
    private String petspecies;
    private String petbreed;
    private String petgender;
    private String petdob;
    private String petcolor;
    private DatabaseReference mDatabase  = FirebaseDatabase.getInstance().getReference();
    private ProgressDialog progressDialog;


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
    public void addpet(Context context, String uId, String petname, String petspecies, String petbreed, String petgender, String petdob, String petcolor) {
        Pet pet = new Pet(petname, petspecies, petbreed, petgender, petdob, petcolor);
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
}
