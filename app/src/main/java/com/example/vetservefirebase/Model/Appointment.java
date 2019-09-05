package com.example.vetservefirebase.Model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Appointment {

    private  String appointmentKey;
    private  String date;
    private  String time;
    private  String extranotes;
    private  String providerKey;
    private  String petKey;
    ArrayList<Services> services;
    private String status;

    public Appointment() {
    }

    public Appointment(String appointmentKey, String date, String time, String extranotes, String providerKey,
                       String petKey, ArrayList<Services> services, String status) {
        this.appointmentKey = appointmentKey;
        this.date = date;
        this.time = time;
        this.extranotes = extranotes;
        this.providerKey = providerKey;
        this.petKey = petKey;
        this.services = services;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppointmentKey() {
        return appointmentKey;
    }

    public void setAppointmentKey(String appointmentKey) {
        this.appointmentKey = appointmentKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExtranotes() {
        return extranotes;
    }

    public void setExtranotes(String extranotes) {
        this.extranotes = extranotes;
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

    public ArrayList<Services> getServices() {
        return services;
    }

    public void setServices(ArrayList<Services> services) {
        this.services = services;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("appointmentKey", appointmentKey);
        result.put("date", date);
        result.put("time", time);
        result.put("extranotes", extranotes);
        result.put("providerKey", providerKey);
        result.put("petKey", petKey);
        result.put("services requested", services);
        return result;
    }
}
