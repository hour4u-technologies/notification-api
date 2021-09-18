package com.codemiro.hour4u.notificationservice.web.request;

import com.codemiro.hour4u.notificationservice.model.TypeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The type Job seeker profile status mail request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerProfileStatusMailRequest implements Serializable {

    private String name;
    private String status;
    private String email;
    private List<TypeModel> types;
    private String notes;
    private String rejectedReason;

}
