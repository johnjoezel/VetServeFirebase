package com.example.vetservefirebase.PetDashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PetDashboardActivity extends AppCompatActivity {

    private static final String TAG = "PetDashboardActivity";
    private Intent intent;
    private String uId, petKey;
    BottomNavigationView bottomNavigationView;
    private Bundle arguments;
    ArrayList<String> photoUrls = new ArrayList<>();
    MyCustomPagerAdapter myCustomPagerAdapter;
    private ViewPager viewPager1,viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private DatabaseReference dRef;
    private ArrayList<String> petKeys = new ArrayList<>();
    GeneralFragment generalFragment = new GeneralFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_dashboard);
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dRef = FirebaseDatabase.getInstance().getReference("pets").child(uId);
        viewPager = findViewById(R.id.viewPager);
        viewPager1 = findViewById(R.id.viewPager1);
        tabLayout =  findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager1);
        getImageUrls();
    }

    private void getImageUrls() {
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Pet pet = ds.getValue(Pet.class);
                    photoUrls.add(pet.getPhotoUrl());
                    petKeys.add(ds.getKey());
                }
                setupimageslider();
                setupViewPager(viewPager1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setupimageslider() {
        myCustomPagerAdapter = new MyCustomPagerAdapter(this, photoUrls, petKeys);
        this.viewPager.setAdapter(myCustomPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                arguments.putString("petKey", petKeys.get(position));
                generalFragment.setArguments(arguments);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(generalFragment, "General");
        adapter.addFragment(new MedicationFragment(), "Medication");
        adapter.addFragment(new SchedulesFragment(), "Schedule");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
