package com.example.vetservefirebase.PetDashboard;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vetservefirebase.R;

import java.util.ResourceBundle;

public class MedicationFragment extends Fragment {

    private static final String TAG = "Medical Fragment";
    private String petKey;
    private Bundle arguments = new Bundle();
    private String uId;

    public MedicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_medication, container, false);
        return view;
    }

}
