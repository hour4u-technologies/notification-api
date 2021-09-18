package com.codemiro.hour4u.notificationservice.domain;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * The type Web notification.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notification")
@QueryEntity
public class WebNotification extends AbstractDocument {

    private String description;

    private String url;

    private String employerId;

    private List<String> referenceRoles;

}
