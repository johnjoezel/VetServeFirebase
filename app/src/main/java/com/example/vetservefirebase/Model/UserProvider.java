package com.example.vetservefirebase.Model;

public class UserProvider {


    String providerID;

    public UserProvider() {
    }

    public UserProvider(String providerID) {
        this.providerID = providerID;
    }

    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }
}
