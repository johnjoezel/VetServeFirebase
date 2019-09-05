package com.example.vetservefirebase.PetDashboard;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyTabLayoutAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArrayList;
    ArrayList<String> titles;
    public MyTabLayoutAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentArrayList = new ArrayList<>();
        this.titles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment, String title){
        fragmentArrayList.add(fragment);
        titles.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}