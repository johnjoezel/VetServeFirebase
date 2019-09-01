package com.example.vetservefirebase.Others;

import com.example.vetservefirebase.Model.ServiceProvider;
import com.google.android.gms.maps.model.Marker;

public class ClinicMarker {
    ServiceProvider provider;
    Marker marker;
    String providerKey;

    public ClinicMarker(ServiceProvider provider, Marker marker, String providerKey) {
        this.provider = provider;
        this.marker = marker;
        this.providerKey = providerKey;
    }

    public ServiceProvider getProvider() {
        return provider;
    }

    public void setProvider(ServiceProvider provider) {
        this.provider = provider;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public String getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
    }
}
