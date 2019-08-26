package com.example.vetservefirebase.Model;

import android.os.Parcel;
import android.os.Parcelable;


public class Pet  implements Parcelable  {

    String pet_name;
    String species;
    String breed;
    String gender;
    String color;
    String status;
    String dob;
    String photoUrl;
    String petKey;



    public Pet() {
    }

    public Pet(String petKey, String pet_name, String species, String breed, String gender, String dob, String color, String photoUrl, String status) {
        this.petKey = petKey;
        this.photoUrl = photoUrl;
        this.pet_name = pet_name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.dob = dob;
        this.color = color;
        this.status = status;
    }

    public Pet(String pet_name, String species, String breed, String gender, String dob, String color, String photoUrl, String status) {
        this.photoUrl = photoUrl;
        this.pet_name = pet_name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.dob = dob;
        this.color = color;
        this.status = status;
    }

    public Pet(Parcel in) {
        petKey = in.readString();
        photoUrl = in.readString();
        pet_name = in.readString();
        species = in.readString();
        breed = in.readString();
        gender = in.readString();
        dob = in.readString();
        color = in.readString();
        status = in.readString();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static final Parcelable.Creator<Pet> CREATOR = new Parcelable.Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(petKey);
        parcel.writeString(photoUrl);
        parcel.writeString(pet_name);
        parcel.writeString(species);
        parcel.writeString(breed);
        parcel.writeString(gender);
        parcel.writeString(dob);
        parcel.writeString(color);
        parcel.writeString(status);
    }

}
