package com.example.vetservefirebase.Model;

public class UserProvider {


    String providerKey;

    public UserProvider() {
    }

    public UserProvider(String providerKey) {
        this.providerKey = providerKey;
    }

    public String getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
    }
}
