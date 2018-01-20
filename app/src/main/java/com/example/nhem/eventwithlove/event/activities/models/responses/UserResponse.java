package com.example.nhem.eventwithlove.event.activities.models.responses;

/**
 * Created by Hau on 1/20/2018.
 */

public class UserResponse {

    String accessToken;

    public UserResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
