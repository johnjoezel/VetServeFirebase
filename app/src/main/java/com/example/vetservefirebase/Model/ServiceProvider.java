package com.example.vetservefirebase.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceProvider implements Parcelable  {

    String clinicname;
    String location;
    String firstname;
    String lastname;
    String phonenumber;

    public ServiceProvider() {
    }

    public ServiceProvider(String clinicname, String location, String firstname, String lastname, String phonenumber) {
        this.clinicname = clinicname;
        this.location = location;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
    }

    public String getClinicname() {
        return clinicname;
    }

    public void setClinicname(String clinicname) {
        this.clinicname = clinicname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


    public ServiceProvider(Parcel in) {
        clinicname = in.readString();
        location = in.readString();
        firstname = in.readString();
        lastname = in.readString();
        phonenumber = in.readString();
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
        parcel.writeString(clinicname);
        parcel.writeString(location);
        parcel.writeString(firstname);
        parcel.writeString(lastname);
        parcel.writeString(phonenumber);
    }
}
