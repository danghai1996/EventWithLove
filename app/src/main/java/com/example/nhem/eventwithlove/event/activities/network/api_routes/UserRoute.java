package com.example.nhem.eventwithlove.event.activities.network.api_routes;

import com.example.nhem.eventwithlove.event.activities.models.requests.UserRequest;
import com.example.nhem.eventwithlove.event.activities.models.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Hau on 1/20/2018.
 */

public interface UserRoute {
    @POST("/api/loginFb")
    Call<UserResponse> login(@Body UserRequest userRequest);
}
