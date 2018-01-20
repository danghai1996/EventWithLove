package com.example.nhem.eventwithlove.event.activities.models.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Hau on 1/20/2018.
 */

public class Event implements Serializable{

    @SerializedName("name")
    String name;
    @SerializedName("address")
    String address;
    @SerializedName("start")
    Long start;

    public Event() {
    }

    public Event(String name, String address, Long timeStart) {
        this.name = name;
        this.address = address;
        this.start = timeStart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTimeStart() {
        return start;
    }

    public void setTimeStart(Long timeStart) {
        this.start = timeStart;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", timeStart='" + start + '\'' +
                '}';
    }
}
