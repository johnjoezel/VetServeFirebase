package com.example.vetservefirebase.PetOwner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vetservefirebase.MainActivity;
import com.example.vetservefirebase.Others.Utils;
import com.example.vetservefirebase.Others.Validation;
import com.example.vetservefirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
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
    Validation validation = new Validation();
    private static String TAG = "Change Password";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Change Password");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ButterKnife.bind(this);
        user  = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
    }

    @OnClick (R.id.btnChangepass) void changePass(){
        String oldpassword = txtoldpassword.getText().toString().trim();
        String newpassword = txtnewpassword.getText().toString().trim();
        String cnewpassword = txtcnewpassword.getText().toString().trim();
        Boolean testOldPass=validation.validateNormalInput(txtoldpassword);
        Boolean testNewPass=validation.validateNormalInput(txtnewpassword);
        Boolean testCNewPass=validation.validateNormalInput(txtcnewpassword);
        if(testOldPass && testNewPass && testCNewPass) {
            AuthCredential credential = EmailAuthProvider.getCredential(email, oldpassword);
            Log.d(TAG, "changePass: " + credential);
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Updating Password");
            progressDialog.show();
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                if (newpassword.equals(cnewpassword)) {
                                    user.updatePassword(newpassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressDialog.dismiss();
                                            if (task.isSuccessful()) {
                                                Utils.showMessage(getApplicationContext(), "Password Updated!");
                                                Utils.setIntent(getApplicationContext(), MainActivity.class);
                                            } else {
                                                Log.d(TAG, "Error password not updated");
                                            }
                                        }
                                    });
                                } else {
                                    txtnewpassword.setError("Password Mismatch");
                                    txtcnewpassword.setError("Password Mismatch");
                                    txtnewpassword.requestFocus();
                                }
                            } else {
                                try {
                                    throw task.getException();
                                } catch (Exception e) {
                                    txtoldpassword.setError(e.getMessage());
                                    txtoldpassword.requestFocus();
                                }
                            }
                        }
                    });
        }else{
            if(!testOldPass)
                txtoldpassword.requestFocus();
            else if(!testNewPass)
                txtnewpassword.requestFocus();
            else if (!testCNewPass)
                txtcnewpassword.requestFocus();
        }
    }
}
