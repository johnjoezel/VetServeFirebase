package com.example.vetservefirebase.ServiceProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.vetservefirebase.AddPet.AddPetActivity;
import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceProvidersList extends AppCompatActivity {

    private DatabaseReference dRef;
    ArrayList<ServiceProvider> providers = new ArrayList<ServiceProvider>();
    ArrayList<String> providerKeys = new ArrayList<>();
    Bundle arguments = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_providers_list);
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List of Service Providers");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        dRef = FirebaseDatabase.getInstance().getReference("providers");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    providerKeys.add(ds.getKey());
                    providers.add(ds.getValue(ServiceProvider.class));
                }
                arguments.putParcelableArrayList("providers",providers);
                arguments.putStringArrayList("providerKeys",providerKeys);
                loadDefaultfragment();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void loadDefaultfragment() {
        Fragment fragment = new ProvidersListView();
        fragment.setArguments(arguments);
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
                fragment.setArguments(arguments);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.providers_container, fragment);
                fragmentTransaction.commitAllowingStateLoss();
                return true;
            case R.id.mapview:
                Fragment fragment1 = new ProvidersMapView();
                fragment1.setArguments(arguments);
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
