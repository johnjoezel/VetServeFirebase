package com.example.vetservefirebase.AddPet;

import com.example.vetservefirebase.Base.BaseView;

public interface AddPetView extends BaseView {
    void addPetSuccess(String msg, String petKey);
    void editPetSuccess(String msg);
}
