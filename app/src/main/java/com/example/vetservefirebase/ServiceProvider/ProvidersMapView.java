package com.example.vetservefirebase.ServiceProvider;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.Others.GeocodingLocation;
import com.example.vetservefirebase.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProvidersMapView extends Fragment{

    private DatabaseReference dRef;

    public ProvidersMapView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_providers_map_view, container, false);
        dRef = FirebaseDatabase.getInstance().getReference("providers");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ServiceProvider serviceProvider = ds.getValue(ServiceProvider.class);
                    String address = serviceProvider.getLocation();
                    String clinicname = serviceProvider.getClinicname();
                    GeocodingLocation locationAddress = new GeocodingLocation();
                    locationAddress.getAddressFromLocation(address, clinicname, getContext(), new GeocoderHandler());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ServiceProvider provider = dataSnapshot.getValue(ServiceProvider.class);
//                String address = provider.getLocation();
//                GeocodingLocation locationAddress = new GeocodingLocation();
//                locationAddress.getAddressFromLocation(address,
//                        getContext(), new GeocoderHandler());
//                Log.d("Mapa", "handleMessage: " + locationAddress.toString() + new GeocoderHandler().obtainMessage());
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        return view;
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            String clinicname;
            double latit = 0; double longit = 0;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    clinicname = bundle.getString("clinicname");
                    locationAddress = bundle.getString("address");
                    latit = bundle.getDouble("latit");
                    longit = bundle.getDouble("longit");
                    break;
                default:
                    locationAddress = null;
                    clinicname = null;
            }
            loadAddress(latit, longit, clinicname);
        }
    }

    private void loadAddress(double latit, double longit, String clinicname) {
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                float zoomLevel = 18.0f; //This goes up to 21
                LatLng gg = new LatLng(latit, longit);
                googleMap.addMarker(new MarkerOptions().position(gg).title(clinicname));
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(gg));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gg, zoomLevel));

            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.listview, menu);
        return;
    }

}
