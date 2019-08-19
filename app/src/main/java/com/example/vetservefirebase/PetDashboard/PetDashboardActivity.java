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
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.vetservefirebase.AddPet.AddPetActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PetDashboardActivity extends AppCompatActivity {

    @BindView(R.id.rightarrow)
    ImageView rightarrow;
    @BindView(R.id.leftarrow)
    ImageView leftarrow;
    private static final String TAG = "PetDashboardActivity";
    String uId;
    Bundle arguments = new Bundle();
    ArrayList<String> photoUrls = new ArrayList<>();
    ArrayList<String> petnames = new ArrayList<>();
    MyCustomPagerAdapter myCustomPagerAdapter;
    private ViewPager viewPager1,viewPager;
    private TabLayout tabLayout;
    private DatabaseReference dRef;
    ArrayList<String> petKeys = new ArrayList<>();
    GeneralFragment generalFragment = new GeneralFragment();
    MedicationFragment medicationFragment = new MedicationFragment();
    SchedulesFragment schedulesFragment = new SchedulesFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dRef = FirebaseDatabase.getInstance().getReference("pets").child(uId);
        getImageUrls();
    }
    private void getImageUrls() {
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Pet pet = ds.getValue(Pet.class);
                        photoUrls.add(pet.getPhotoUrl());
                        petKeys.add(ds.getKey());
                        petnames.add(pet.getPet_name());

                    }
                    setContentView(R.layout.activity_pet_dashboard);
                    ButterKnife.bind(PetDashboardActivity.this);
                    viewPager = findViewById(R.id.viewPager);
                    viewPager1 = findViewById(R.id.viewPager1);
                    tabLayout =  findViewById(R.id.tabLayout);
                    tabLayout.setupWithViewPager(viewPager1);
                    setupimageslider();
                }else{
                    setContentView(R.layout.blanklayout);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public String passUid(){
        return uId;
    }

    @OnClick({R.id.rightarrow, R.id.leftarrow, R.id.editpet}) void leftandright(View imageView){
        if(imageView == rightarrow){
            if(viewPager.getCurrentItem() < viewPager.getRight())
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
        }
        if(imageView == leftarrow){
            if(viewPager.getCurrentItem() > 0)
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1,true);
        }

    }
    @OnClick (R.id.editpet) void editpet(){
        finish();
    }

    private void setupimageslider() {
        myCustomPagerAdapter = new MyCustomPagerAdapter(this, photoUrls, petnames);
        viewPager.setAdapter(myCustomPagerAdapter);
        if(viewPager.getCurrentItem() == 0){
            if(petKeys.size() == 1) {
                rightarrow.setVisibility(View.INVISIBLE);
                leftarrow.setVisibility(View.INVISIBLE);
            }else
                leftarrow.setVisibility(View.INVISIBLE);
        }
        arguments.putString("petKey", petKeys.get(viewPager1.getCurrentItem()));
        generalFragment.setArguments(arguments);
        medicationFragment.setArguments(arguments);
        schedulesFragment.setArguments(arguments);
        setupViewPager(viewPager1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "leftandright: clicked" + petKeys.size());
                if(position > 0) {
                    leftarrow.setVisibility(View.VISIBLE);
                    if (position == petKeys.size() - 1)
                        rightarrow.setVisibility(View.INVISIBLE);
                }else if(petKeys.size() == 1){
                    leftarrow.setVisibility(View.INVISIBLE);
                    rightarrow.setVisibility(View.INVISIBLE);
                }
                else {
                    leftarrow.setVisibility(View.INVISIBLE);
                    rightarrow.setVisibility(View.VISIBLE);
                }
                arguments.putString("petKey", petKeys.get(position));
                generalFragment.setArguments(arguments);
                medicationFragment.setArguments(arguments);
                schedulesFragment.setArguments(arguments);
                setupViewPager(viewPager1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        Intent intent = new Intent(this, AddPetActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(generalFragment, "General");
        adapter.addFragment(medicationFragment, "Medication");
        adapter.addFragment(schedulesFragment, "Schedule");
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
