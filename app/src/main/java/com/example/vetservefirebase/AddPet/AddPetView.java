package com.example.vetservefirebase.AddPet;

import com.example.vetservefirebase.Base.BaseView;
import com.example.vetservefirebase.Others.Breedname;
import java.util.ArrayList;

public interface AddPetView extends BaseView {
    void addPetSuccess();
    void breedsforspinner(ArrayList<String> data);
    void addPetError(String errcode, String errmessage);
    void setProgressVisibility(boolean visibility);
}
