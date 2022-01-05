package com.javabot.slack.dto;

public class SlackRegisterUserResponse {

    private String status;
    private String message;

    public SlackRegisterUserResponse() {
    }

    public SlackRegisterUserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
