package com.example.vetservefirebase.ServiceProvider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.vetservefirebase.AddPet.AddPetActivity;
import com.example.vetservefirebase.R;

public class ServiceProvidersList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_providers_list);
        loadDefaultfragment();

    }

    private void loadDefaultfragment() {
        Fragment fragment = new ProvidersListView();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.providers_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listview:
                Fragment fragment = new ProvidersListView();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.providers_container, fragment);
                fragmentTransaction.commitAllowingStateLoss();
                return true;
            case R.id.mapview:
                Fragment fragment1 = new ProvidersMapView();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction1.replace(R.id.providers_container, fragment1);
                fragmentTransaction1.commitAllowingStateLoss();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
