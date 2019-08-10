package com.example.vetservefirebase.SignUp;

import android.net.Uri;

import com.example.vetservefirebase.Base.BaseView;
import com.google.android.gms.tasks.Task;

interface SignUpView extends BaseView {
    void signUpSuccess();
    void signUpError(String errcode, String errmessage);
    void setProgressVisibility(boolean visibility);
}
