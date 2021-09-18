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

package com.codemiro.hour4u.notificationservice.service.consumer.notification;

import com.codemiro.hour4u.notificationservice.model.WebNotificationRequest;
import com.codemiro.hour4u.notificationservice.service.business.WebNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Web notification mq consumer.
 */
@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RabbitListener(queues = "Q_WEB_NOTIFICATION")
public class WebNotificationMQConsumer {

    private final WebNotificationService service;

    /**
     * Add new notification.
     *
     * @param notificationRequest the notification request
     */
    @RabbitHandler
    public void addNewNotification(WebNotificationRequest notificationRequest) {
        service.create(notificationRequest);
    }


}
