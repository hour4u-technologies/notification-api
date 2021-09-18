package com.codemiro.hour4u.notificationservice.service.consumer.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemiro.hour4u.notificationservice.constants.MailConstants;
import com.codemiro.hour4u.notificationservice.model.EmploymentStatusRequest;
import com.codemiro.hour4u.notificationservice.model.SESFrom;
import com.codemiro.hour4u.notificationservice.util.EmailUtility;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Employment mail service.
 */
@Service("employmentMailService")
@Slf4j
@RabbitListener(queues = "Q_EMPLOYMENT_MAIL")
public class EmploymentMailService {

	@Autowired
    private EmailUtility emailUtility;


    /**
     * Employment mail notification.
     *
     * @param request the request
     * @throws Exception the exception
     */
    @RabbitHandler
	public void employmentMailNotification(EmploymentStatusRequest request) throws Exception {
		log.info("====Employment Status Change Email=");
		String[] recipients = {request.getEmail()};
        String subject = "Employment Status Changed";
        log.info(subject);
        Map<String, String> keyValueMap = new HashMap<>();     
        
        keyValueMap.put("status", request.getStatus());
        
        emailUtility.prepareAndSend(SESFrom.CONTACT, recipients, null, null, subject,
                MailConstants.EMPLOYMENT_MAIL, keyValueMap, null);
		
	}
}
