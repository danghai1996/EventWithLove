package com.example.nhem.eventwithlove.event.activities.network.api_routes;

import com.example.nhem.eventwithlove.event.activities.models.requests.QuestionRequest;
import com.example.nhem.eventwithlove.event.activities.models.responses.QuestionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Hau on 1/21/2018.
 */

public interface QuestionRoute {
    @POST("/api/questions")
    Call<QuestionResponse> sendQuestion(@Body QuestionRequest questionRequest);

}
