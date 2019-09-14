package com.example.vetservefirebase.FreelanceVet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vetservefirebase.MainActivity;
import com.example.vetservefirebase.Model.User;
import com.example.vetservefirebase.R;
import com.example.vetservefirebase.SignIn.SignInActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VetMainActivity extends AppCompatActivity {
    private DatabaseReference dRef;
    private static final String TAG = "Vet Dashboard Activity";
    private static final String TAG_HOME = "My Pets";
    private static final String TAG_PATIENT = "My Patients";
    private static final String TAG_CLIENT = "My Clients";
    private static final String TAG_APPOINTMENT = "My Appointments";
    private Toolbar toolbar;
    private int state;
    private Handler mHandler;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View navHeader;
    TextView headerDisplayname;
    ImageView headerBackground, headerDisplaypic;
    private String[] activityTitles;
    private FirebaseUser user;
    private String email;
    private int navItemIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_main);
        state = 0;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHandler = new Handler();
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.vet_nav_view);
        navHeader = navigationView.getHeaderView(0);
        headerDisplaypic = navHeader.findViewById(R.id.headerDisplaypic);
        headerDisplayname = navHeader.findViewById(R.id.headerDisplayname);
        headerBackground = navHeader.findViewById(R.id.img_header_bg);
        activityTitles = getResources().getStringArray(R.array.vet_item_activity_titles);
        user = FirebaseAuth.getInstance().getCurrentUser();

    }
}
