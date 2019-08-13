package com.example.vetservefirebase.PetOwnerProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.vetservefirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.txtoldpassword)
    EditText txtoldpassword;
    @BindView(R.id.txtnewpassword)
    EditText txtnewpassword;
    @BindView(R.id.txtcnewpassword)
    EditText txtcnewpassword;
    private FirebaseUser user ;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        user  = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
    }

    @OnClick (R.id.btnChangepass) void changePass(){
        String oldpassword = txtoldpassword.getText().toString().trim();
        credentials 
    }
}
