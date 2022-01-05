package com.javabot.slack.dto;

import java.util.Map;

public class SlackPostMessageResponse {

    private Map<String, String> response;

    public SlackPostMessageResponse() {
    }

    public SlackPostMessageResponse(Map<String, String> response) {
        this.response = response;
    }

    public Map<String, String> getResponse() {
        return response;
    }

    public void setResponse(Map<String, String> response) {
        this.response = response;
    }
}
