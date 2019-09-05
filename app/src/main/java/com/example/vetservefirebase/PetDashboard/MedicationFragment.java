package com.example.vetservefirebase.PetDashboard;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vetservefirebase.R;

import java.util.ResourceBundle;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MedicationFragment extends Fragment {

    private static final String TAG = "Medical Fragment";
    private String petKey;
    private String uId;

    @BindView(R.id.medication)
    TextView medication;

    public MedicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_medication, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

}
