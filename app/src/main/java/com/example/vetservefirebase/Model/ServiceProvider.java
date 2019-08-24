package com.example.vetservefirebase.Model;

public class ServiceProvider {

    String clinicName;
    String clinicAddress;
    String opening;
    String closing;
    String clinicPhone;

    public ServiceProvider() {
    }

    public ServiceProvider(String clinicName, String clinicAddress, String opening, String closing, String clinicPhone) {
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.opening = opening;
        this.closing = closing;
        this.clinicPhone = clinicPhone;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public String getOpening() {
        return opening;
    }

    public String getClosing() {
        return closing;
    }

    public String getClinicPhone() {
        return clinicPhone;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public void setClinicPhone(String clinicPhone) {
        this.clinicPhone = clinicPhone;
    }
}
