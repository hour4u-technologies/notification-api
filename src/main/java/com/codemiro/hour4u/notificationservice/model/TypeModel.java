package com.codemiro.hour4u.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Type model.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeModel implements Serializable {

    private String name;
    private String level;
    private String status;
    private String typeId;

}
