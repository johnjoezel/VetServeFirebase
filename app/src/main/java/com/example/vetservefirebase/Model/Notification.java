package com.example.vetservefirebase.Model;

public class Notification {

    private  String appointmentKey;
    private  String providerKey;
    private  String petKey;

    public Notification() {
    }

    public Notification(String appointmentKey, String providerKey, String petKey){
        this.appointmentKey = appointmentKey;
        this.providerKey = providerKey;
        this.petKey = petKey;
    }

    public String getAppointmentKey() {
        return appointmentKey;
    }

    public void setAppointmentKey(String appointmentKey) {
        this.appointmentKey = appointmentKey;
    }

    public String getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
    }

    public String getPetKey() {
        return petKey;
    }

    public void setPetKey(String petKey) {
        this.petKey = petKey;
    }
}
