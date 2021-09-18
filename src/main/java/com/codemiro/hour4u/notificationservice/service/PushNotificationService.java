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

package com.codemiro.hour4u.notificationservice.service;

import com.codemiro.hour4u.notificationservice.firebase.FCMService;
import com.codemiro.hour4u.notificationservice.model.PushNotificationRequest;
import com.codemiro.hour4u.notificationservice.web.request.AppNotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

/**
 * The type Push notification service.
 */
@Service("pushNotificationService")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RabbitListener(queues = "Q_PUSH_NOTIFICATION")
public class PushNotificationService {

    private FCMService fcmService;

    /**
     * Profile approved.
     *
     * @param request the request
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @RabbitHandler
    public void profileApproved(final AppNotificationRequest request) throws ExecutionException, InterruptedException {
        log.info("Mobile Notif :: title #" + request.getTitle() + ", message #" +request.getMessage()  + ", token #" +request.getToken());
        fcmService.sendMessageToToken(PushNotificationRequest.builder()
                .message(request.getMessage())
                .title(request.getTitle())
                .token(request.getToken())
                .topic("hour4u")
                .build());
    }
}
