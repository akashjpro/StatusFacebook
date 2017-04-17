package com.status.aka.model;

/**
 * Created by Aka on 4/11/2017.
 */

public class User {
    String id;
    String name;
    String email;
    String urlProfile;
    String gender;
    String birthday;
    String location;

    public User() {
    }

    public User(String id, String name, String email, String urlProfile, String gender, String birthday, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.urlProfile = urlProfile;
        this.gender = gender;
        this.birthday = birthday;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
