package com.example.vetservefirebase.SignUp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;


import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.vetservefirebase.Base.BaseActivity;
import com.example.vetservefirebase.Others.ShowAlert;
import com.example.vetservefirebase.R;
import com.example.vetservefirebase.SignIn.SignInActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.vetservefirebase.Others.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SignUpActivity extends BaseActivity implements SignUpView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar ;
    @BindView(R.id.txtemail)
    EditText email ;
    @BindView(R.id.txtpassword)
    EditText password ;
    @BindView(R.id.txtcpassword)
    EditText cpassword;
    @BindView(R.id.txtdisplayname)
    EditText displayname;
    @BindView(R.id.imgProfpic)
    ImageView imgProfpic;
    private SignUpPresenterImpl signUpPresenter ;
    private FirebaseAuth mAuth ;
    private Intent intent;
    ArrayList<String> data;
    private int IMG_REQUEST= 1;
    static String TAG = "kwekwe";
    private Uri photoPath;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        intent = getIntent();
        data= intent.getStringArrayListExtra("data");
        Log.d(TAG, data.toString());
        mAuth = FirebaseAuth.getInstance();
        signUpPresenter = new SignUpPresenterImpl(mAuth);
        signUpPresenter.attachView(this);
    }


    @OnClick(R.id.btnRegister) void onSignUpButtonClick() {
        Boolean result = signUpPresenter.validation(bitmap, email.getText().toString().trim(), displayname.getText().toString().trim(), password.getText().toString().trim(), cpassword.getText().toString().trim());
        Log.d("kani?", result.toString());
        if(result) {
//            imgProfpic.setDrawingCacheEnabled(true);
//            imgProfpic.buildDrawingCache();
//            Bitmap bitmap = ((BitmapDrawable) imgProfpic.getDrawable()).getBitmap();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            byte[] byteArray = baos.toByteArray();
            this.data.add(displayname.getText().toString().trim());
            signUpPresenter.signUp(email.getText().toString().trim(), password.getText().toString().trim(), data, this, photoPath);
        }
    }

    @OnClick(R.id.imgProfpic) void uploadpicture(){
        selectimage();
    }

    public void selectimage(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null){
            photoPath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoPath);
                imgProfpic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signUpPresenter.detachView();
    }


    @Override
    public void signUpSuccess() {
        Utils.showMessage(this, "Successfully Registered!");
        Utils.setIntent(this, SignInActivity.class);
    }

    @Override
    public void signUpError(String errcode, String errmessage) {
        if(errcode == "pm"){
            password.setError(errmessage);
            cpassword.setError(errmessage);
            password.requestFocus();
        }else if(errcode == "np"){
            ShowAlert.showAlert(this, errmessage);
        }else if(errcode == "ee"){
            email.setError(errmessage);
            email.requestFocus();
        }else if(errcode == "default"){
            email.setError(errmessage);
            email.requestFocus();
        }else if(errcode == "en"){
            displayname.setError(errmessage);
            displayname.requestFocus();
        }else if(errcode == "ep"){
            password.setError(errmessage);
            password.requestFocus();
        }else if(errcode == "ERROR_WEAK_PASSWORD"){
            password.setError(errmessage);
            password.requestFocus();
        }else if(errcode == "ecp"){
            cpassword.setError(errmessage);
            cpassword.requestFocus();
        }
    }

    @Override
    public void setProgressVisibility(boolean visibility) {
        if (visibility)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }
}

