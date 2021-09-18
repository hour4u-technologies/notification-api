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

package com.codemiro.hour4u.notificationservice.firebase;

import com.codemiro.hour4u.notificationservice.model.PushNotificationRequest;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * The type Fcm service.
 */
@Slf4j
@Service
public class FCMService {

    /**
     * Send message.
     *
     * @param data    the data
     * @param request the request
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException   the execution exception
     */
    public void sendMessage(Map<String, String> data, PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithData(data, request);
        String response = sendAndGetResponse(message);
        log.info("Message response#" + response);
    }

    /**
     * Send message without data.
     *
     * @param request the request
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException   the execution exception
     */
    public void sendMessageWithoutData(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithoutData(request);
        String response = sendAndGetResponse(message);
        log.info("Message response (without data)#" + response);
    }

    /**
     * Send message to token.
     *
     * @param request the request
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException   the execution exception
     */
    public void sendMessageToToken(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request);
        log.info("Message request (to token)#" + message.toString());
        String response = sendAndGetResponse(message);
        log.info("Message response (to token)#" + response);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private AndroidConfig getAndroidConfig(Map<String, String> data, PushNotificationRequest request) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis())
                .setCollapseKey(request.getTopic())
                .setPriority(AndroidConfig.Priority.HIGH)
                .putAllData(data)
                .setNotification(AndroidNotification.builder()
                        .setSound(NotificationParameter.SOUND.getValue())
                        .setColor("#57C7B6")
                        .setIcon("")
                        .setDefaultVibrateTimings(true)
                        .setPriority(AndroidNotification.Priority.HIGH)
                        .setTag(request.getTopic())
                        .setTitle(request.getTitle())
                        .setBody(request.getMessage())
                        .setVisibility(AndroidNotification.Visibility.PUBLIC)
                        .setChannelId("testchannel1")
                        .setClickAction("FCM_PLUGIN_ACTIVITY").build()).build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
        Map<String, String> map = new HashMap();

        map.put("title", request.getTitle());
        map.put("body", request.getMessage());
        map.put("notId", "10");
        map.put("jobId", request.getJobId() !=null ?  request.getJobId() : "" );
        map.put("surveyID", "ewtawgreg-gragrag-rgarhthgbad");
        map.put("android_channel_id", "testchannel1");
        return getPreconfiguredMessageBuilder(map, request).setToken(request.getToken())
                .build();
    }

    private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(new HashMap(), request).setTopic(request.getTopic())
                .build();
    }

    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(data, request).putAllData(data).setTopic(request.getTopic())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(Map<String, String> data, PushNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(data, request);
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setNotification(Notification.builder()
                                    .setTitle(request.getTitle())
                                    .setBody(request.getMessage())
                        .build());
    }
}
