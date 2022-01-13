package com.example.proapp.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Job {
    String title, description, expiry_date, attachment, status, email, number, location, requirements;
    int time_of_day, mode_of_work, working_hours, salary;
    long profession;

    @Id
    long id;

    public Job() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getProfession() {
        return profession;
    }

    public void setProfession(long profession) {
        this.profession = profession;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTime_of_day() {
        return time_of_day;
    }

    public void setTime_of_day(int time_of_day) {
        this.time_of_day = time_of_day;
    }

    public int getMode_of_work() {
        return mode_of_work;
    }

    public void setMode_of_work(int mode_of_work) {
        this.mode_of_work = mode_of_work;
    }

    public int getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(int working_hours) {
        this.working_hours = working_hours;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
