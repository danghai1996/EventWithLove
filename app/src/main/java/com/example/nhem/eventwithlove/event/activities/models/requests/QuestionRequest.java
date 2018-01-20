package com.example.nhem.eventwithlove.event.activities.models.requests;

/**
 * Created by Hau on 1/21/2018.
 */

public class QuestionRequest {
    private String eId;
    private String content;

    public QuestionRequest(String eId, String content) {
        this.eId = eId;
        this.content = content;
    }

    public QuestionRequest() {
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
