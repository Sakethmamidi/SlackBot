package com.javabot.slack.dto;

import java.util.List;

public class SlackPostMessage {

    private List<String> users;
    private SlackMessage message;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public SlackMessage getMessage() {
        return message;
    }

    public void setMessage(SlackMessage message) {
        this.message = message;
    }
}
