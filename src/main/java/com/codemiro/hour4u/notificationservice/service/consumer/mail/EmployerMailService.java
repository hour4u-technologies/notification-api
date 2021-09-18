/*
 *
 *  *
 *  *  Copyright 2020  Codemiro Technologies Pvt Ltd
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *       https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and  limitations under the License.
 *  *
 *
 */

package com.codemiro.hour4u.notificationservice.service.consumer.mail;


import com.codemiro.hour4u.notificationservice.constants.MailConstants;
import com.codemiro.hour4u.notificationservice.model.MailRequest;
import com.codemiro.hour4u.notificationservice.model.SESFrom;
import com.codemiro.hour4u.notificationservice.util.EmailUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Employer mail service.
 */
@Service("employerMailService")
@Slf4j
@RabbitListener(queues = "Q_WELCOME_MAIL")
public class EmployerMailService {

    @Autowired
    private EmailUtility emailUtility;

    @Value("${app.url}")
    private String baseUrl;

    /**
     * Welcome mail notification.
     *
     * @param mailRequest the mail request
     * @throws Exception the exception
     */
    @RabbitHandler
    public void welcomeMailNotification(MailRequest mailRequest) throws Exception {
        String[] recipients = {mailRequest.getEmail()};
        String subject = "Welcome to Hour4u";
        Map<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("name", mailRequest.getName());
        keyValueMap.put("email", mailRequest.getEmail());
        keyValueMap.put("otp", mailRequest.getOtp());
        keyValueMap.put("baseUrl", baseUrl);
        emailUtility.prepareAndSend(SESFrom.CONTACT, recipients, null, null, subject,
                MailConstants.EMPLOYER_WELCOME_MAIL, keyValueMap, null);
    }    
}
