package com.codemiro.hour4u.notificationservice.web.request;

import lombok.*;

import java.util.Map;

/**
 * The type App notification request.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppNotificationRequest {

    private String title;
    private String message;
    private String topic;
    private String token;
    private Map<String, String> data;
    private String jobId;

}
