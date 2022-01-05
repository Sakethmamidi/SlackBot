package com.javabot.slack.controller;

import com.javabot.slack.cache.SlackUserWebhookUrlStore;
import com.javabot.slack.client.SlackApiClient;
import com.javabot.slack.dto.SlackPostMessage;
import com.javabot.slack.dto.SlackPostMessageResponse;
import com.javabot.slack.dto.SlackRegisterUserResponse;
import com.javabot.slack.dto.SlackUserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slack")
public class SlackMessageController {

    @Autowired
    SlackApiClient slackApiClient;
    @Autowired
    SlackUserWebhookUrlStore userWebhookUrlStore;

    @PostMapping("register")
    public ResponseEntity<SlackRegisterUserResponse> registerUser(@RequestBody SlackUserRegister user) {
        if (userWebhookUrlStore.setUserWebhookUrlMap(user)) {
            return ResponseEntity.ok(new SlackRegisterUserResponse("SUCCESS", "webhook url is stored"));
        }
        return ResponseEntity.internalServerError().body(new SlackRegisterUserResponse("FAILURE", "webhook url is not stored"));
    }

    @PostMapping("/send")
    public ResponseEntity<SlackPostMessageResponse> postMessage(@RequestBody SlackPostMessage message) {
        return ResponseEntity.ok(slackApiClient.postMessage(message));
    }
}
