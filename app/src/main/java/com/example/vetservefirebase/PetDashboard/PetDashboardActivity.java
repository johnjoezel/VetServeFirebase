package com.example.vetservefirebase.PetDashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.vetservefirebase.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PetDashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Intent intent;
    private String uId, petKey;
    BottomNavigationView bottomNavigationView;
    private Bundle arguments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_dashboard);
        intent = getIntent();
        uId = intent.getStringExtra("uId");
        petKey = intent.getStringExtra("petKey");
        Log.d("qweqwe", uId + petKey);
        bottomNavigationView =  findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        arguments = new Bundle();
        PetProfileFragment petProfileFragment = new PetProfileFragment();
        arguments.putString("uId", uId);
        arguments.putString("petKey", petKey);
        petProfileFragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(R.id.pet_fragment_container, petProfileFragment, "my screen").commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
