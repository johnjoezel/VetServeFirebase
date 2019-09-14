package com.example.vetservefirebase.Model;

import java.util.ArrayList;

public class Clinic {


    private  String name;
    private  String location;
    private  String contact;


    public Clinic() {
    }

    public Clinic(String name, String location, String contact) {
        this.name = name;
        this.location = location;
        this.contact = contact;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
