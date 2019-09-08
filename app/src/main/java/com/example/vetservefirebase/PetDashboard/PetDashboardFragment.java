package com.example.vetservefirebase.PetDashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.vetservefirebase.AddPet.AddPetActivity;
import com.example.vetservefirebase.Model.Appointment;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PetDashboardFragment extends Fragment implements  ViewPager.OnPageChangeListener {

    @BindView(R.id.rightarrow)
    ImageView rightarrow;
    @BindView(R.id.leftarrow)
    ImageView leftarrow;
    @BindView(R.id.swiperLayout)
    RelativeLayout swiperLayout;
    @BindView(R.id.blanklayout)
    LinearLayout blanklayout;
    @BindView(R.id.editpet)
    Button editpet;
    private static final String TAG = "PetDashboardActivity";
    String uId, petKey;
    Pet mypet;
    Bundle generalArguments = new Bundle();
    Bundle scheduleArguments = new Bundle();
    Bundle arguments;
    ArrayList<Pet> pets = new ArrayList<Pet>();
    ArrayList<String> petKeys = new ArrayList<>();
    ArrayList<Appointment> appointments;
    MyCustomPagerAdapter myCustomPagerAdapter;
    MyTabLayoutAdapter myTabLayoutAdapter;
    private ViewPager petInfoViewPager,petViewPager;
    private TabLayout tabLayout;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("pets");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        arguments = getArguments();
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pet_dashboard, container, false);
        ButterKnife.bind(this, view);
        swiperLayout.setVisibility(View.INVISIBLE);
        blanklayout.setVisibility(View.INVISIBLE);
        editpet.setVisibility(View.INVISIBLE);
        petViewPager = view.findViewById(R.id.petViewPager);
        petInfoViewPager = view.findViewById(R.id.petInfoViewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        myCustomPagerAdapter = new MyCustomPagerAdapter(this.getActivity(), pets);
        petViewPager.setAdapter(myCustomPagerAdapter);
        petViewPager.addOnPageChangeListener(this);
        getPets();
        return view;
    }

    private void setupViewPager() {
        myTabLayoutAdapter = new MyTabLayoutAdapter(getChildFragmentManager(), petKey);
        myTabLayoutAdapter.addFragment(new GeneralFragment(),"General");
        myTabLayoutAdapter.addFragment(new MedicationFragment(), "Medication");
        myTabLayoutAdapter.addFragment(new SchedulesFragment(), "Schedule");
        petInfoViewPager.setAdapter(myTabLayoutAdapter);
        tabLayout.setupWithViewPager(petInfoViewPager);
    }


    private void checkList() {
        if (pets.isEmpty()) {
            swiperLayout.setVisibility(View.GONE);
            blanklayout.setVisibility(View.VISIBLE);
            editpet.setVisibility(View.GONE);
        }
        else {
            swiperLayout.setVisibility(View.VISIBLE);
            editpet.setVisibility(View.VISIBLE);
            blanklayout.setVisibility(View.GONE);
        }
    }

    private void getPets() {
        dRef.child(uId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    Pet pet = dataSnapshot.getValue(Pet.class);
                    if(!pet.getStatus().equals("removed")) {
                        petKeys.add(dataSnapshot.getKey());
                        pets.add(pet);
                        myCustomPagerAdapter.notifyDataSetChanged();
                        checkList();
                    }
                }
                if (pets.size() == 1) {
                    rightarrow.setVisibility(View.INVISIBLE);
                    leftarrow.setVisibility(View.INVISIBLE);
                    mypet = pets.get(0);
                    petKey = dataSnapshot.getKey();
                } else {
                    rightarrow.setVisibility(View.VISIBLE);
                    if (arguments.getString("petKey") != null) {
                        petKey = arguments.getString("petKey");
                        petViewPager.setCurrentItem(petKeys.indexOf(petKey));
                        return;
                    }
                    petKey = petKeys.get(petViewPager.getCurrentItem());
                    mypet = pets.get(petViewPager.getCurrentItem());
                    setupViewPager();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                myCustomPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                myTabLayoutAdapter.notifyDataSetChanged();
                myCustomPagerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @OnClick({R.id.rightarrow, R.id.leftarrow, R.id.editpet}) void leftandright(View imageView){
        if(imageView == rightarrow){
            if(petViewPager.getCurrentItem() < petViewPager.getRight())
                petViewPager.setCurrentItem(petViewPager.getCurrentItem()+1,true);
        }
        if(imageView == leftarrow){
            if(petViewPager.getCurrentItem() > 0)
                petViewPager.setCurrentItem(petViewPager.getCurrentItem()-1,true);
        }

    }


    @OnClick (R.id.editpet) void editpet(){
        Intent intent = new Intent(getContext(), AddPetActivity.class);
        intent.putExtra("pet", mypet);
        intent.putExtra("petKey", petKey);
        Log.d(TAG, "editpet: "+ mypet);
        startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position > 0) {
            leftarrow.setVisibility(View.VISIBLE);
            if (position == pets.size() - 1)
                rightarrow.setVisibility(View.INVISIBLE);
            else
                rightarrow.setVisibility(View.VISIBLE);
        }
        else if(position < 1){
            leftarrow.setVisibility(View.INVISIBLE);
            rightarrow.setVisibility(View.VISIBLE);
        }
        mypet = pets.get(position);
        petKey = petKeys.get(position);
        setupViewPager();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public String setPetKey(){
        return petKey;
    }
}
