package com.javabot.slack.client;

import com.javabot.slack.cache.SlackUserWebhookUrlStore;
import com.javabot.slack.dto.SlackMessage;
import com.javabot.slack.dto.SlackPostMessage;
import com.javabot.slack.dto.SlackPostMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class SlackApiClient {

    private final RestTemplate restTemplate;
    private final SlackUserWebhookUrlStore userWebhookUrlStore;

    @Value("${slack.api}")
    private String slackChatApiEndpoint;

    @Autowired
    public SlackApiClient(RestTemplateBuilder restTemplateBuilder, SlackUserWebhookUrlStore userWebhookUrlStore) {
        this.restTemplate = restTemplateBuilder.build();
        this.userWebhookUrlStore = userWebhookUrlStore;
    }

    public SlackPostMessageResponse postMessage(final SlackPostMessage slackPostMessage) {

        final HttpEntity<SlackMessage> request = new HttpEntity<>(slackPostMessage.getMessage());

        Map<String, String> response = new HashMap<>();
        for (String user :
                slackPostMessage.getUsers()) {

            String endpoint = slackChatApiEndpoint + userWebhookUrlStore.getUserWebhookUrl(user);

            final String apiResponse = this.restTemplate.postForObject(endpoint, request, String.class);
            response.put(user, apiResponse);
        }
        return new SlackPostMessageResponse(response);
    }

}
