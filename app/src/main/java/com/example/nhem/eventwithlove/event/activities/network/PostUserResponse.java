package com.example.nhem.eventwithlove.event.activities.network;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by NHEM on 20/01/2018.
 */

public class PostUserResponse implements Serializable {
    int code;
    @SerializedName("accessToken")
    String accessToken;

    public PostUserResponse(int code, String accessToken) {
        this.code = code;
        this.accessToken = accessToken;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "PostUserResponse{" +
                "code=" + code +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
