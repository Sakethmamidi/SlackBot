package com.javabot.slack.cache;

import com.javabot.slack.dto.SlackUserRegister;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class SlackUserWebhookUrlStore {

    private final ConcurrentMap<String, String> userWebhookUrlMap = new ConcurrentHashMap<>();

    public String getUserWebhookUrl(String userId) {
        return userWebhookUrlMap.get(userId);
    }

    public boolean setUserWebhookUrlMap(SlackUserRegister user) {
        this.userWebhookUrlMap.put(user.getUserId(), user.getWebhookUrl());
        return true;
    }
}
