package com.example.vetservefirebase.SignIn;

import android.content.Context;

import com.example.vetservefirebase.Base.BasePresenter;

public interface SignInPresenter extends BasePresenter<SignInView>{
    void login(String email, String password, Context context);
    void checkLogin();
}
