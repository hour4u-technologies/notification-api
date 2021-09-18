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

package com.codemiro.hour4u.notificationservice.util;

import com.amazonaws.services.pinpoint.model.MessageType;
import com.amazonaws.services.pinpoint.model.SendMessagesResult;
import com.codemiro.hour4u.notificationservice.firebase.FCMService;
import com.codemiro.hour4u.notificationservice.firebase.FCMServiceV2;
import com.codemiro.hour4u.notificationservice.model.PushNotificationRequest;
import com.codemiro.hour4u.notificationservice.model.SESFrom;
import com.codemiro.hour4u.notificationservice.repository.NotificationRepository;
import com.codemiro.hour4u.notificationservice.service.consumer.sms.AuthSMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * The type Initialization.
 */
//@Component
@Slf4j
public class Initialization {

    @Autowired
    private AuthSMSService authSMSService;

    /**
     * The Fcm service.
     */
    @Autowired
    FCMService fcmService;

    /**
     * The Repository.
     */
    @Autowired
    NotificationRepository repository;

    @Autowired
    private EmailUtility emailUtility;

    @Autowired
    private SMSUtility smsUtility;


    /**
     * Init.
     *
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @PostConstruct
    public void init() throws ExecutionException, InterruptedException {

        Map<String, String> data = new HashMap<>();
        data.put("jobId", "fwISONjBr5I");
        fcmService.sendMessageToToken(PushNotificationRequest.builder()
                .data(data)
                .message("Your Profile is approved. tttt")
                .jobId("dfase343dfa")
                .title("test")
                //.topic("JOB_SEEKER_PROFILE_APPROVAL")
                .token("ccEej-eQOHA:APA91bFwgP5iYkZ6xA5K7IdkAlsFlthGVPlnUXOcVP-j4M3oKgZLzMZHOsCZZqNdAGG2OEUhrPbVpcEEKbpTlO9BE4tgGaATyEE-JONFwJ2xHHnU0z7iaG9TpL6J0b8i4zfgeUvSuxNe")
                .build());
    }

}
