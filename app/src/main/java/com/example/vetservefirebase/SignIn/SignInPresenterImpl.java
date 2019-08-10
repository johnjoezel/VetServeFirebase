package com.example.vetservefirebase.SignIn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;



public class SignInPresenterImpl implements SignInPresenter {
    private FirebaseAuth auth ;
    private SignInView signInView;
    private ProgressDialog progressDialog;
    private String errmessage, errorcode;
    public SignInPresenterImpl(FirebaseAuth auth) {
        this.auth = auth;
    }
    @Override
    public void login(String email, String password, Context context) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            if(TextUtils.isEmpty(email)) {
                errorcode = "emptyemail";
                errmessage = "Please enter your email address";
            }else{
                errorcode = "emptypassword";
                errmessage = "Please enter a password";
            }
            signInView.seterror(errorcode, errmessage);
        } else {
            progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) signInView, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if(!task.isSuccessful()) {
                                try {
                                    throw task.getException();
                                } catch(FirebaseAuthInvalidCredentialsException e) {
                                    errorcode = e.getErrorCode();
                                    errmessage = e.getMessage();
                                } catch(Exception e) {
                                    errorcode = "default";
                                    errmessage = e.getMessage();
                                }
                                signInView.seterror(errorcode, errmessage);
                            } else {
                                signInView.loginSuccess();
                            }
                        }
                    });
        }
    }

    @Override
    public void checkLogin() {
        if (auth.getCurrentUser() != null)
            signInView.isLogin(true);
        else
            signInView.isLogin(false);
    }

    @Override
    public void attachView(SignInView view) {
        signInView = view ;
    }

    @Override
    public void detachView() {
        signInView = null ;
    }
}