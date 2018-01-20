package com.example.nhem.eventwithlove.event.activities.models.requests;

/**
 * Created by Hau on 1/20/2018.
 */

public class UserRequest {

    private String access_token;

    public UserRequest(String fbAccessToken) {
        this.access_token = fbAccessToken;
    }

    public String getFbAccessToken() {
        return access_token;
    }

    public void setFbAccessToken(String fbAccessToken) {
        this.access_token = fbAccessToken;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "fbAccessToken='" + access_token + '\'' +
                '}';
    }
}
