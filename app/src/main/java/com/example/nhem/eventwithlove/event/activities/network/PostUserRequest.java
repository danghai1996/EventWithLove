package com.example.nhem.eventwithlove.event.activities.network;

/**
 * Created by NHEM on 20/01/2018.
 */

public class PostUserRequest {
    String name;
    String gender;
    String email;
    String phone;
    String link;

    public PostUserRequest(String name, String gender, String email, String phone, String link) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLink() {
        return link;
    }
}
