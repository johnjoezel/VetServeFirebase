package com.example.vetservefirebase;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.PetDashboard.PetDashboardFragment;
import com.example.vetservefirebase.ServiceProvider.ServiceProviderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class BlankFragment extends Fragment{

    BottomNavigationView bottomNav;
    private Handler mHandler;
    ArrayList<Pet> pets;
    ArrayList<String> petKeys;
    String petKey;
    public static int bottomNavItemIndex = 0;
    private static final String TAG_MYPETS = "My Pets";
    private static final String TAG_PROVIDERS = "Providers";
    public static String CURRENT_TAG = TAG_MYPETS;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        if(getArguments() != null) {
            Bundle arguments = getArguments();
            petKeys = arguments.getStringArrayList("petKeys");
            pets = arguments.getParcelableArrayList("pets");
            if(getArguments().getInt("index") == 0) {
                bottomNavItemIndex = arguments.getInt("index");
            }else if(getArguments().getInt("index") == 1){
                bottomNavItemIndex = arguments.getInt("index");
            }
        }
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_blank, container, false);
        mHandler = new Handler();

        bottomNav = view.findViewById(R.id.bottomnav);
        setUpBottomNavigationView();
        loadDefaultFragment();
        return view;
    }

    public ArrayList<Pet> setPets(){
        return pets;
    }

    private void loadDefaultFragment() {

        selectBottomNavMenu();
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getDefaultFragment();
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.pet_fragment_container1, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

    }

    private void selectBottomNavMenu() {
        bottomNav.getMenu().getItem(bottomNavItemIndex).setChecked(true);
    }

    private Fragment getDefaultFragment() {
        switch (bottomNavItemIndex) {
            case 0:
                // home
                PetDashboardFragment petDashboardFragment = new PetDashboardFragment();
                Bundle arguments = new Bundle();
                if(getArguments().getString("petKey") != null)
                    arguments.putString("petKey", getArguments().getString("petKey"));
                arguments.putStringArrayList("petKeys", petKeys);
                arguments.putParcelableArrayList("pets", pets);
                petDashboardFragment.setArguments(arguments);
                return petDashboardFragment;
            case 1:
                ServiceProviderFragment serviceProviderFragment = new ServiceProviderFragment();
                return serviceProviderFragment;
            default:
                return new BlankFragment();
        }
    }

    private void setUpBottomNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.mypets:
                        bottomNavItemIndex = 0;
                        CURRENT_TAG = TAG_MYPETS;
                        break;
                    case R.id.providers:
                        bottomNavItemIndex = 1;
                        CURRENT_TAG = TAG_PROVIDERS;
                        break;
                    default:
                        bottomNavItemIndex = 0;
                }

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadDefaultFragment();
                return true;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(bottomNavItemIndex == 0) {
            inflater.inflate(R.menu.addpet, menu);
        }
        if(bottomNavItemIndex == 1)
            inflater.inflate(R.menu.searchlinic, menu);
        return;
    }

}
