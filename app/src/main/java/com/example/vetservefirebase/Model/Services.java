package com.example.vetservefirebase.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Services implements Parcelable{

    private String key;
    private String servicename;
    private boolean isChecked = false;

    public Services() {
    }

    public Services(String servicename) {
        this.servicename = servicename;
    }

    public Services(String key, String servicename, Boolean isChecked) {
        this.key = key;
        this.servicename = servicename;
        this.isChecked = isChecked;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public Services(Parcel in) {
        key = in.readString();
        servicename = in.readString();
    }

    public static final Parcelable.Creator<Services> CREATOR = new Parcelable.Creator<Services>() {
        @Override
        public Services createFromParcel(Parcel in) {
            return new Services(in);
        }

        @Override
        public Services[] newArray(int size) {
            return new Services[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(servicename);
    }
}
