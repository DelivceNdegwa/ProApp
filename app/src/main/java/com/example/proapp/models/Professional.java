package com.example.proapp.models;

public class Professional {
    String name, email, password, occupation, description, skills, image, phone_number;
    int  working_hours, price_per_hour, id;
    float rating;

    public Professional() {

    }

    public Professional(String name, String email, String occupation, String phone_number) {
        this.name = name;
        this.email = email;
        this.occupation = occupation;
        this.phone_number = phone_number;
    }

    public Professional(int id, String name, String email, String occupation, String phone_number, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.occupation = occupation;
        this.phone_number = phone_number;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(int working_hours) {
        this.working_hours = working_hours;
    }

    public int getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(int price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


    public String getOccupation() { return occupation; }

    public void setOccupation(String occupation) { this.occupation = occupation;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
