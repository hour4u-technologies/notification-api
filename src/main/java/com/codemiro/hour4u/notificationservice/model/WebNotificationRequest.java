package com.codemiro.hour4u.notificationservice.model;

import lombok.*;

import java.util.List;

/**
 * The type Web notification request.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WebNotificationRequest {

    private String description;

    private String url;

    private String employerId;

    private List<String> referenceRoles;


}
