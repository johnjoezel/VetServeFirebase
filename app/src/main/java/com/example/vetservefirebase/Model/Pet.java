package com.example.vetservefirebase.Model;

public class Pet {

    String uId;
    String pet_name;
    String species;
    String breed;
    String gender;
    String color;
    String dob;

    public Pet() {
    }

    public Pet(String pet_name, String species, String breed, String gender, String dob, String color) {
        this.uId = uId;
        this.pet_name = pet_name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.dob = dob;
        this.color = color;
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

}
