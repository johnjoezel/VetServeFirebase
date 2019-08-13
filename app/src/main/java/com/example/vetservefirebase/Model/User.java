package com.example.vetservefirebase.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

    String uId;
    String firstname;
    String middlename;
    String lastname;
    String contact;
    String displayname;
    String bday;



    String address;
    String phoneNumber;
    String photoUrl;

    //Default Constructor
    public User(){

    }

    public User( String firstname, String middlename, String lastname, String bday, String contact, String address, String displayname, String photoUrl){
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.bday = bday;
        this.contact = contact;
        this.address = address;
        this.displayname = displayname;
        this.photoUrl = photoUrl;
    }



    protected User(Parcel in) {
        contact = in.readString();
        uId = in.readString();
        firstname = in.readString();
        middlename = in.readString();
        lastname = in.readString();
        bday = in.readString();
        displayname = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        photoUrl = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };





    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uid) {
        uId = uid;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(contact);
        parcel.writeString(uId);
        parcel.writeString(firstname);
        parcel.writeString(middlename);
        parcel.writeString(lastname);
        parcel.writeString(bday);
        parcel.writeString(displayname);
        parcel.writeString(address);
        parcel.writeString(phoneNumber);
        parcel.writeString(photoUrl);
    }
}