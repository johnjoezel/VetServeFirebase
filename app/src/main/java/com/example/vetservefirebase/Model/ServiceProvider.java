package com.example.vetservefirebase.Model;

import android.os.Parcel;
import android.os.Parcelable;


public class ServiceProvider implements Parcelable  {


    private  String firstname;
    private  String middlename;
    private  String lastname;
    private  String address;
    private  String contact;
    private  String usertype;
    private String subscribed;

    public ServiceProvider() {
    }

    public ServiceProvider(String firstname, String middlename, String lastname, String address, String contact, String usertype, String subscribed) {

        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.contact = contact;
        this.usertype = usertype;
        this.subscribed = subscribed;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(String subscribed) {
        this.subscribed = subscribed;
    }


    public ServiceProvider(Parcel in) {
        firstname = in.readString();
        middlename = in.readString();
        lastname = in.readString();
        address = in.readString();
        contact = in.readString();
        usertype = in.readString();
        subscribed = in.readString();
    }


    public static final Parcelable.Creator<ServiceProvider> CREATOR = new Parcelable.Creator<ServiceProvider>() {
        @Override
        public ServiceProvider createFromParcel(Parcel in) {
            return new ServiceProvider(in);
        }

        @Override
        public ServiceProvider[] newArray(int size) {
            return new ServiceProvider[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstname);
        parcel.writeString(middlename);
        parcel.writeString(lastname);
        parcel.writeString(address);
        parcel.writeString(contact);
        parcel.writeString(usertype);
        parcel.writeString(subscribed);
    }
}
