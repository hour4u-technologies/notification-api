package com.codemiro.hour4u.notificationservice.service.business;

import com.codemiro.hour4u.notificationservice.domain.QWebNotification;
import com.codemiro.hour4u.notificationservice.domain.WebNotification;
import com.codemiro.hour4u.notificationservice.model.WebNotificationRequest;
import com.codemiro.hour4u.notificationservice.repository.NotificationRepository;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * The type Web notification service.
 */
@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebNotificationService {

    private final NotificationRepository repository;

    /**
     * <p>Finds {@link WebNotification} for admin </p>
     * SUPER_ADMIN,
     * JOB_ADMIN,
     * JOB_SUPERVISOR,
     * EMPLOYER_ADMIN,
     * EMPLOYER_STAFF,
     * ON_BOARDING_STAFF,
     * JOB_SEEKER,
     *
     * @param employerId the employer id
     * @return found list of {@link WebNotification}
     */
    @Transactional(readOnly = true)
    public List<WebNotification> getAll(String employerId) {
        if(employerId != null) {
            return (List<WebNotification>) repository
                    .findAll(QWebNotification.webNotification.employerId.eq(employerId), Sort.by("createdOn").descending());
        } else {
            return repository
                    .findAll(Sort.by("createdOn").descending());
        }
    }

    /**
     * <p>creates new {@link WebNotification}</p>
     *
     * @param request the request
     * @return {@link WebNotification}
     */
    @Transactional
    public WebNotification create(final WebNotificationRequest request) {
        return repository.save(WebNotification.builder()
                .description(request.getDescription())
                .referenceRoles(request.getReferenceRoles())
                .url(request.getUrl())
                .employerId(request.getEmployerId())
                .build());
    }

}
