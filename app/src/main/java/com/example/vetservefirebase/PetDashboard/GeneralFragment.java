package com.example.vetservefirebase.PetDashboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vetservefirebase.R;

public class GeneralFragment extends Fragment {

    private static final String TAG = "General Fragment";
    private OnFragmentInteractionListener mListener;
    Bundle arguments = new Bundle();

    public GeneralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_general, container, false);
        arguments = getArguments();
        Log.d(TAG, "onCreateView: " + arguments.getString("petKey"));
        return view;

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
