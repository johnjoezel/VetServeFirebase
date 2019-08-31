package com.example.vetservefirebase.PetDashboard;

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
import com.example.vetservefirebase.AddPet.AddPetActivity;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PetDashboardFragment extends Fragment {

    @BindView(R.id.rightarrow)
    ImageView rightarrow;
    @BindView(R.id.leftarrow)
    ImageView leftarrow;
    private static final String TAG = "PetDashboardActivity";
    String uId, petKey;
    Pet pet;
    Bundle arguments;
    ArrayList<Pet> pets;
    ArrayList<String> petKeys = new ArrayList<>();
    MyCustomPagerAdapter myCustomPagerAdapter;
    private ViewPager viewPager1,viewPager;
    private TabLayout tabLayout;
    private DatabaseReference dRef;

    GeneralFragment generalFragment;
    MedicationFragment medicationFragment;
    SchedulesFragment schedulesFragment;
    LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        arguments = getArguments();
        generalFragment = new GeneralFragment();
        medicationFragment = new MedicationFragment();
        schedulesFragment = new SchedulesFragment();
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dRef = FirebaseDatabase.getInstance().getReference("pets").child(uId);
        pets = arguments.getParcelableArrayList("pets");
        petKeys = arguments.getStringArrayList("petKeys");
        Log.d(TAG, "onCreate: " + pets);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        View view;
        if(!pets.isEmpty()) {
            view =  inflater.inflate(R.layout.fragment_pet_dashboard, container, false);
            ButterKnife.bind(this, view);
            viewPager = view.findViewById(R.id.viewPager);
            viewPager1 = view.findViewById(R.id.viewPager1);
            tabLayout = view.findViewById(R.id.tabLayout);
            tabLayout.setupWithViewPager(viewPager1);
            setupimageslider();
        }else{
            view =  inflater.inflate(R.layout.blanklayout, container, false);
            Button addpet = view.findViewById(R.id.addpet);
            addpet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), AddPetActivity.class);
                    startActivity(intent);
                }
            });
        }
        return view;
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


    private void setupimageslider() {
        myCustomPagerAdapter = new MyCustomPagerAdapter(inflater,this.getActivity(), pets);
        viewPager.setAdapter(myCustomPagerAdapter);
        if(arguments.getString("petKey") != null){
            petKey = arguments.getString("petKey");
            viewPager.setCurrentItem(petKeys.indexOf(petKey));
        }else if(viewPager.getCurrentItem() == 0){
            if(pets.size() == 1) {
                rightarrow.setVisibility(View.INVISIBLE);
                leftarrow.setVisibility(View.INVISIBLE);
            }else
                leftarrow.setVisibility(View.INVISIBLE);
        }
        arguments.putParcelable("pet", pets.get(viewPager1.getCurrentItem()));
        pet = pets.get(viewPager1.getCurrentItem());
        petKey = petKeys.get(viewPager1.getCurrentItem());
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
                Log.d(TAG, "leftandright: clicked" + pets.size());
                if(position > 0) {
                    leftarrow.setVisibility(View.VISIBLE);
                    if (position == pets.size() - 1)
                        rightarrow.setVisibility(View.INVISIBLE);
                    else
                        rightarrow.setVisibility(View.VISIBLE);
                }else if(pets.size() == 1){
                    leftarrow.setVisibility(View.INVISIBLE);
                    rightarrow.setVisibility(View.INVISIBLE);
                }
                else {
                    leftarrow.setVisibility(View.INVISIBLE);
                    rightarrow.setVisibility(View.VISIBLE);
                }
                arguments.putParcelable("pet", pets.get(position));
                pet = pets.get(position);
                petKey = petKeys.get(position);
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
    @OnClick (R.id.editpet) void editpet(){
        Intent intent = new Intent(getContext(), AddPetActivity.class);
        intent.putExtra("pet", pet);
        intent.putExtra("petKey", petKey);
        Log.d(TAG, "editpet: "+ pet);
        startActivity(intent);
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
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
