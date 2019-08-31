package com.example.vetservefirebase.ServiceProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.location.Location;

import com.example.vetservefirebase.Others.ClinicMarker;
import com.example.vetservefirebase.Others.GeocodingLocation;
import com.google.android.gms.location.LocationListener;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.api.LogDescriptor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProvidersMapView extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {


    private ArrayList<ServiceProvider> providers;
    private ArrayList<String> providerKey;
    @BindView(R.id.viewClinicname)
    TextView clinicName;
    @BindView(R.id.viewCliniclocation)
    TextView clinicLocation;
    @BindView(R.id.linkToClinicProfile)
    TextView linkToClinicProfile;
    @BindView(R.id.closeview)
    TextView closeview;
    @BindView(R.id.cardviewclinic)
    CardView cardviewclinic;
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private Marker clinicsMarker;
    private static final int Request_User_Location_Code = 99;



    public ProvidersMapView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        providers = arguments.getParcelableArrayList("providers");
        providerKey = arguments.getStringArrayList("providerKeys");
        View view = inflater.inflate(R.layout.fragment_providers_map_view, container, false);
        ButterKnife.bind(this, view);
        cardviewclinic.setVisibility(View.INVISIBLE);
        GeocodingLocation locationAddress = new GeocodingLocation();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkUserLocationPermission();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.listview, menu);
        return;
    }

    public boolean checkUserLocationPermission(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }else{
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("last nalang ka", "onLocationChanged: " + lastLocation);
        lastLocation = location;
        if(currentUserLocationMarker != null){
            currentUserLocationMarker.remove();
        }
        GeocodingLocation locationAddress = new GeocodingLocation();
        for (int i = 0; i < providers.size(); i++) {
            locationAddress.getAddressFromLocation(providers.get(i), providerKey.get(i), getContext(), new GeocoderHandler());
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("You are here!");
        float zoomLevel = 12.0f;
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        currentUserLocationMarker = mMap.addMarker(markerOptions);
        currentUserLocationMarker.showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoomLevel));
        if(googleApiClient != null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            mMap.setOnMarkerClickListener(this);
        }
    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("atayatay", marker.toString() + " == " + currentUserLocationMarker);
        if(marker.equals(currentUserLocationMarker)){
        }else {
            cardviewclinic.setVisibility(View.VISIBLE);
            ClinicMarker clinicMarker = (ClinicMarker) marker.getTag();
            ServiceProvider provider = clinicMarker.getProvider();
            clinicName.setText(provider.getClinicname());
            clinicLocation.setText(provider.getLocation());
            linkToClinicProfile.setPaintFlags(linkToClinicProfile.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            linkToClinicProfile.setText("View Details");
            closeview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cardviewclinic.setVisibility(View.INVISIBLE);
                    clinicName.setText("");
                    clinicLocation.setText("");
                    linkToClinicProfile.setText("");
                }
            });
            linkToClinicProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProviderProfileActivity.class);
                    intent.putExtra("provider", provider);
                    startActivity(intent);
                }
            });
            Log.d("marker id", "onMarkerClick: " + clinicMarker);
        }
        return false;
    }

    protected synchronized void buildGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Request_User_Location_Code:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
                        if(googleApiClient == null){
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }else{
                    Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            ServiceProvider provider;
            String providerKey;
            double latit = 0; double longit = 0;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    provider = bundle.getParcelable("provider");
                    providerKey = bundle.getString("providerKey");
                    latit = bundle.getDouble("latit");
                    longit = bundle.getDouble("longit");
                    break;
                default:
                    providerKey = null;
                    provider = null;
            }
            loadAddress(latit, longit, provider,providerKey);
        }
    }



    private void loadAddress(double latit, double longit, ServiceProvider provider, String providerKey) {
        LatLng gg = new LatLng(latit, longit);
        MarkerOptions markerOptions = new MarkerOptions().position(gg).title(provider.getClinicname());
        Marker marker = mMap.addMarker(markerOptions);
        ClinicMarker clinicMarker = new ClinicMarker(provider, marker);
        marker.setTag(clinicMarker);
    }

}
