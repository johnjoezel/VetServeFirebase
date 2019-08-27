package com.example.vetservefirebase.Model;

public class ServiceProvider {

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
}
