package com.codemiro.hour4u.notificationservice.service.consumer.mail;

import com.codemiro.hour4u.notificationservice.constants.MailConstants;
import com.codemiro.hour4u.notificationservice.model.SESFrom;
import com.codemiro.hour4u.notificationservice.util.EmailUtility;
import com.codemiro.hour4u.notificationservice.web.request.JobSeekerProfileStatusMailRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Job seeker mail service.
 */
@Service("jobSeekerMailService")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RabbitListener(queues = "Q_JOB_SEEKER_MAIL")
public class JobSeekerMailService {

    private EmailUtility emailUtility;

    private static final String ACTIVE = "active";
    private static final String REJECTED = "rejected";

    /**
     * Profile approved.
     *
     * @param profileStatusRequest the profile status request
     * @throws Exception the exception
     */
    @RabbitHandler
    public void profileApproved(JobSeekerProfileStatusMailRequest profileStatusRequest) throws Exception {
        log.info(">>>> Mail Notif <<<<<" + profileStatusRequest.getStatus());
        String[] recipients = {profileStatusRequest.getEmail()};
        String subject = "";
        String mailConstants = null;
        Map<String, Object> keyValueMap = new HashMap<>();
        keyValueMap.put("name", profileStatusRequest.getName());
        keyValueMap.put("notes", profileStatusRequest.getNotes());
        if (profileStatusRequest.getStatus().equalsIgnoreCase(ACTIVE)) {
            subject = "Your profile approved";
            mailConstants = MailConstants.PROFILE_STATUS;
            if (profileStatusRequest.getTypes() != null && !profileStatusRequest.getTypes().isEmpty()) {
                mailConstants = MailConstants.APPROVED_WITH_CATEGORY;
                keyValueMap.put("typeList", profileStatusRequest.getTypes());
            }
            keyValueMap.put("status", "approved");
        } else if (profileStatusRequest.getStatus().equalsIgnoreCase(REJECTED)) {
            subject = "Your profile rejected";
            mailConstants = MailConstants.PROFILE_STATUS;
            keyValueMap.put("status", "rejected");
        }
        emailUtility.prepareAndSend(SESFrom.CONTACT, recipients, null, null, subject,
                mailConstants, keyValueMap, null);
    }


}
