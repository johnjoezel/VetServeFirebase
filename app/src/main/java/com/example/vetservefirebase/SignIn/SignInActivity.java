package com.example.vetservefirebase.SignIn;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import com.example.vetservefirebase.Base.BaseActivity;
import com.example.vetservefirebase.MainActivity;
import com.example.vetservefirebase.Others.ShowAlert;
import com.example.vetservefirebase.R;
import com.example.vetservefirebase.SignUp.Personal_Information;
import com.example.vetservefirebase.SignUp.SignUpActivity;
import com.example.vetservefirebase.Others.Utils;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignInActivity extends BaseActivity implements SignInView {
    private static final String TAG = "SignInActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final int RC_SIGN_IN = 9001;
    public static String userEmail;
    @BindView(R.id.signInEmail)
    EditText email;
    @BindView(R.id.signInPassword)
    EditText password ;
    private SignInPresenter signInPresenter;
    //Declare firebase objects
    private FirebaseAuth mAuth;

    //Realtime Database

    //Facebook API Objects
//    CallbackManager callbackManager;
//    LoginButton fb_login;

    //Declare Google API Objects
    private GoogleApiClient mGoogleApiClient;
    private SignInButton login_google;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setTitle("Sign In");
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        signInPresenter = new SignInPresenterImpl(mAuth);
        signInPresenter.attachView(this);
        signInPresenter.checkLogin();
        //Set up an AuthStateListener that responds to changes in the user's sign-in state


        //for facebook login
//        callbackManager = CallbackManager.Factory.create();
//        fb_login = (LoginButton) findViewById(R.id.login_button_fb);
//        fb_login.setPermissions("email", "public_profile");
//        fb_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                //goMainScreen();
//                handleFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "facebook:onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Toast.makeText(SignInActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "facebook:onError", error);
//            }
//        });
    }

    @OnClick(R.id.signup) void onSignUpButtonClick() {
        Utils.setIntent(this, Personal_Information.class);
    }

    @OnClick(R.id.btnlogin) void onLoginButtonClick() {
        String email_text = email.getText().toString().trim();
        String password_text = password.getText().toString().trim();
        signInPresenter.login(email_text, password_text, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signInPresenter.detachView();
    }

    @Override
    public void showValidationError(String message) {
        ShowAlert.showAlert(this, message);
    }

    @Override
    public void loginSuccess() {
        Utils.showMessage(this, "Login Success !");
        Utils.setIntent(this, MainActivity.class);
    }

    @Override
    public void seterror(String errorcode, String message) {
        if(errorcode == "ERROR_WRONG_PASSWORD"){
            password.setError(message);
            password.requestFocus();
        }else if(errorcode == "ERROR_INVALID_EMAIL"){
            email.setError(message);
            email.requestFocus();
        }else if(errorcode == "emptyemail"){
            email.setError(message);
            email.requestFocus();
        }else if(errorcode == "emptypassword"){
            password.setError(message);
            password.requestFocus();
        }else{
            email.setError(message);
            email.requestFocus();
        }
    }


    @Override
    public void isLogin(boolean isLogin) {
        if (isLogin) {
            Utils.setIntent(this, MainActivity.class);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public Context getContext() {
        return this;
    }

    //Facebook get creadentials
//    private void handleFacebookAccessToken(AccessToken accessToken) {
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
//
//        fb_login.setEnabled(false);
//        showProgressDialog();
//        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                //Add user details to firebase database
//                writeNewUser();
//                Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
//                progressDialog.dismiss();
//                if (!task.isSuccessful()) {
//                    Log.w(TAG, "signInWithCredential", task.getException());
//                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
//                    fb_login.setEnabled(true);
//                }else{
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                }
//            }
//        });
//    }

//    private void writeNewUser(){
//        FirebaseUser firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
//
//        if(firebaseuser !=null) {
//            String name = firebaseuser.getDisplayName();
//            String email = firebaseuser.getEmail();
//            String photoUrl = firebaseuser.getPhotoUrl().toString();
//            String uId =  firebaseuser.getUid();
//            String provider = null;
//            String providerId = null;
//
//            for (UserInfo profile : firebaseuser.getProviderData()) {
//                // Id of the provider (ex: google.com)
//                provider = profile.getProviderId();
//                providerId = profile.getUid();
//            }
//
//            final UserWithProvider user = new UserWithProvider(name, email, photoUrl, uId, provider);
//            //Add user to firebase database
//            final DatabaseReference userRef = mDatabase.child("users");
//
//            final String providerUid = providerId;
//            userRef.child(providerUid).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if(dataSnapshot.exists()){
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    }else{
//                        userRef.child(providerUid).setValue(user);
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Toast.makeText(SignInActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//    }
//
}
