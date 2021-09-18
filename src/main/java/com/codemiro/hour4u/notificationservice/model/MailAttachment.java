package com.codemiro.hour4u.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Mail attachment.
 */
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class MailAttachment {

    private String name;
    private byte[] content;
    private String contentType;

}
