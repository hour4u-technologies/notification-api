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
import com.codemiro.hour4u.notificationservice.model.SESFrom;
import com.codemiro.hour4u.notificationservice.util.EmailUtility;
import com.codemiro.hour4u.notificationservice.web.request.MailOtpRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The type User mail service.
 */
@Service("userMailService")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RabbitListener(queues = "Q_USER_MAIL")
public class UserMailService {

    private EmailUtility emailUtility;

    /**
     * Welcome mail notification.
     *
     * @param name  the name
     * @param email the email
     * @throws Exception the exception
     */
    @RabbitHandler
    public void welcomeMailNotification(String name, String email) throws Exception {
        String[] recipients = {email};
        String subject = "Welcome to Hour4u";
        log.info(subject);
        Map<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("name", name);
        emailUtility.prepareAndSend(SESFrom.CONTACT, recipients, null, null, subject,
                MailConstants.WELCOME_MAIL, keyValueMap, null);
    }

    /**
     * Send otp.
     *
     * @param request the request
     * @throws Exception the exception
     */
    @RabbitHandler
    public void sendOtp(MailOtpRequest request) throws Exception {
        String[] recipients = {request.getEmail()};
        String subject = "One Time Password";
        Map<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("otp", request.getOtp());
        emailUtility.prepareAndSend(SESFrom.CONTACT, recipients, null, null, subject,
                MailConstants.FORGOT_PASSWORD, keyValueMap, null);
    }
}
