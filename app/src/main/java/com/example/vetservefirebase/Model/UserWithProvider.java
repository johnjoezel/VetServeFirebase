package com.example.vetservefirebase.Model;

public class UserWithProvider {

    public String name;
    public String email;
    public String address;
    public String phoneNumber;
    public String photoUrl;
    public String uId;
    public String provider;

    //Default Constructor
    public UserWithProvider(){

    }

    public UserWithProvider(String name, String user_email, String photoUrl, String uId, String provider){
        this.name = name;
        this.email = user_email;
        this.photoUrl = photoUrl;
        this.uId = uId;
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }



}

