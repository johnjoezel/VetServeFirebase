package com.example.vetservefirebase.AddPet;

import com.example.vetservefirebase.Base.BaseView;

public interface AddPetView extends BaseView {
    void addPetSuccess();
    void addPetError(String errcode, String errmessage);
    void setProgressVisibility(boolean visibility);
}
