package com.example.nhem.eventwithlove.event.activities.network.api_routes;

import com.example.nhem.eventwithlove.event.activities.models.requests.UserRequest;
import com.example.nhem.eventwithlove.event.activities.models.responses.UserResponse;
import com.example.nhem.eventwithlove.event.activities.network.response.UserDataResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Hau on 1/20/2018.
 */

public interface UserRoute {
    @POST("/api/u/user")
    Call<UserDataResponse> login(@Body UserRequest userRequest);
}
