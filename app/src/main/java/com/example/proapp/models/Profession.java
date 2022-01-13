package com.example.proapp.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Profession {
    String name, icon, created_at;
    int views;

    @Id
    long id;

    public Profession() {
    }

    public Profession(String name, String icon, String created_at) {
        this.name = name;
        this.icon = icon;
        this.created_at = created_at;
    }

    public Profession(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
