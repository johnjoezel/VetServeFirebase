package com.example.vetservefirebase.PetDashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.vetservefirebase.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PetDashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Intent intent;
    private String uId, petKey;
    BottomNavigationView bottomNavigationView;
    private Bundle arguments;
    String photoUrls[] = {"https://firebasestorage.googleapis.com/v0/b/vetservefirebase.appspot.com/o/PetImage%2F75b769dc-22f2-4bd0-8187-dc888d5bdf99.jpg?alt=media&token=c8fa3871-46b5-41a9-af54-c7ae9f14a9bd"};
    MyCustomPagerAdapter myCustomPagerAdapter;
    private ViewPager viewPager1,viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_dashboard);
//        intent = getIntent();
//        uId = intent.getStringExtra("uId");
//        petKey = intent.getStringExtra("petKey");
//        Log.d("qweqwe", uId + petKey);
//        bottomNavigationView =  findViewById(R.id.bottomnav);
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        arguments = new Bundle();
//        PetProfileFragment petProfileFragment = new PetProfileFragment();
//        arguments.putString("uId", uId);
//        arguments.putString("petKey", petKey);
//        petProfileFragment.setArguments(arguments);
//        getSupportFragmentManager().beginTransaction().replace(R.id.pet_fragment_container, petProfileFragment, "my screen").commit();


        viewPager = findViewById(R.id.viewPager);
        viewPager1 = findViewById(R.id.viewPager1);
        tabLayout =  findViewById(R.id.tabLayout);
        setupViewPager(viewPager1);
        tabLayout.setupWithViewPager(viewPager1);
        myCustomPagerAdapter = new MyCustomPagerAdapter(this, photoUrls);
        viewPager.setAdapter(myCustomPagerAdapter);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GeneralFragment(), "General");
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
