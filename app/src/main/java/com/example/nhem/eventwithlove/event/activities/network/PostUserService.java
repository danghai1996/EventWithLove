package com.example.nhem.eventwithlove.event.activities.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by NHEM on 20/01/2018.
 */

public interface PostUserService {
    @POST("/api/u/user")
    Call<PostUserResponse> postUser(@Body PostUserRequest userRequest);
}
