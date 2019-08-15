package com.example.vetservefirebase.Model;

public class Pet {

    String uId;
    String pet_name;
    String species;
    String breed;
    String gender;
    String color;
    String dob;
    String photoUrl;



    public Pet() {
    }

    public Pet(String pet_name, String species, String breed, String gender, String dob, String color, String photoUrl) {
        this.photoUrl = photoUrl;
        this.pet_name = pet_name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.dob = dob;
        this.color = color;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
    public String getPet_name() {
        return pet_name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getColor() {
        return color;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
