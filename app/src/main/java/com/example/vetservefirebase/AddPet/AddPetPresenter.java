package com.example.vetservefirebase.AddPet;

import android.content.Context;

import com.example.vetservefirebase.Base.BasePresenter;

public interface AddPetPresenter extends BasePresenter<AddPetView> {
    void addpet(Context context, String uId, String petname, String petspecies, String petbreed, String petgender, String petdob, String petcolor, String photoUrl);
    void updatepet(Context context, String petKey, String uId, String petname, String petspecies, String petbreed, String petgender, String petdob, String petcolor, String photoUrl);
}
