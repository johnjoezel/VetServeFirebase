package com.example.vetservefirebase.Model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Appointment {

    private String uId;
    private String date_requested;
    private String date;
    private String time;
    private String extranotes;
    private String providerKey;
    private String petKey;
    private String services_requested;
    private String status;

    public Appointment() {
    }

    public Appointment(String uId, String petKey, String date, String time, String extranotes,
                       String providerKey, String services_requested, String status, String date_requested) {
        this.uId = uId;
        this.date = date;
        this.time = time;
        this.extranotes = extranotes;
        this.providerKey = providerKey;
        this.petKey = petKey;
        this.services_requested = services_requested;
        this.status = status;
        this.date_requested = date_requested;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getDate_requested() {
        return date_requested;
    }

    public void setDate_requested(String date_requested) {
        this.date_requested = date_requested;
    }

    public String getServices_requested() {
        return services_requested;
    }

    public void setServices_requested(String services_requested) {
        this.services_requested = services_requested;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("date", date);
        result.put("time", time);
        result.put("extranotes", extranotes);
        result.put("petKey", petKey);
        result.put("providerKey", providerKey);
        result.put("uId", uId);
        result.put("status", status);
        result.put("services_requested", services_requested);
        result.put("date_requested", date_requested);
        return result;
    }
}
