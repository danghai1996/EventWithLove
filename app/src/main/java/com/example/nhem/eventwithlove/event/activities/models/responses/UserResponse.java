package com.example.nhem.eventwithlove.event.activities.models.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hau on 1/20/2018.
 */

public class UserResponse implements Serializable {

    String access_token;

    public UserResponse() {
    }

    public UserResponse(String accessToken) {
        this.access_token = accessToken;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "accessToken='" + access_token + '\'' +
                '}';
    }
}
