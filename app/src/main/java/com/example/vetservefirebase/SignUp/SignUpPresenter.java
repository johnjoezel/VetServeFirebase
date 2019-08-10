package com.example.vetservefirebase.SignUp;



import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.example.vetservefirebase.Base.BasePresenter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface SignUpPresenter extends BasePresenter<SignUpView> {
    void signUp(String email, String password, ArrayList<String> data, Context context, Uri photopath);
    boolean validation(Bitmap bitmap, String email, String displayname, String password, String cpassword);
}
