package com.example.nhem.eventwithlove.event.activities.models.requests;

/**
 * Created by Hau on 1/20/2018.
 */

public class UserRequest {

    private String fbAccessToken;

    public UserRequest(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
    }

    public String getFbAccessToken() {
        return fbAccessToken;
    }

    public void setFbAccessToken(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "fbAccessToken='" + fbAccessToken + '\'' +
                '}';
    }
}
