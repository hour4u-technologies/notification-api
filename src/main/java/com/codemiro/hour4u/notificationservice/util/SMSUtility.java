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

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.pinpoint.AmazonPinpoint;
import com.amazonaws.services.pinpoint.model.AddressConfiguration;
import com.amazonaws.services.pinpoint.model.ChannelType;
import com.amazonaws.services.pinpoint.model.DirectMessageConfiguration;
import com.amazonaws.services.pinpoint.model.MessageRequest;
import com.amazonaws.services.pinpoint.model.MessageType;
import com.amazonaws.services.pinpoint.model.SMSMessage;
import com.amazonaws.services.pinpoint.model.SendMessagesRequest;
import com.amazonaws.services.pinpoint.model.SendMessagesResult;
import com.codemiro.hour4u.notificationservice.web.exception.SMSException;

import lombok.extern.slf4j.Slf4j;


/**
 * The type Sms utility.
 */
@Slf4j @Service
public class SMSUtility {

    /**
     * The constant ENCODING.
     */
    public static final String ENCODING = "UTF-8";
    /**
     * The constant APP_ID.
     */
    public static final String APP_ID = "9027b9d3279d42459c899a12370d494e";
    /**
     * The constant senderId.
     */
    public static String senderId = "hHOUR4U";

    private final AmazonPinpoint amazonPinpoint;

    /**
     * Instantiates a new Sms utility.
     *
     * @param amazonPinpoint the amazon pinpoint
     */
    @Autowired
    public SMSUtility(AmazonPinpoint amazonPinpoint) {
        this.amazonPinpoint = amazonPinpoint;
    }

    /**
     * Send sms message send messages result.
     *
     * @param message     the message
     * @param phoneNumber the phone number
     * @param messageType the message type
     * @return the send messages result
     */
    public SendMessagesResult sendSMSMessage(String message, String phoneNumber, MessageType messageType) {
        try {
            Map<String, AddressConfiguration> addressMap =
                    new HashMap<>();

            addressMap.put(phoneNumber, new AddressConfiguration()
                    .withChannelType(ChannelType.SMS));

            SendMessagesRequest request = new SendMessagesRequest()
                    .withApplicationId(APP_ID)
                    .withMessageRequest(new MessageRequest()
                            .withAddresses(addressMap)
                            .withMessageConfiguration(new DirectMessageConfiguration()
                                    .withSMSMessage(new SMSMessage()
                                            .withBody(message)
                                            .withMessageType(messageType)
                                            .withMessageType(messageType.toString())
                                            .withSenderId(senderId)
                                            .withKeyword("HOUR4U")
                                    )
                            )
                    );
            SendMessagesResult result= amazonPinpoint.sendMessages(request);
            log.info(result.toString());
            return result;
        } catch (Exception ex) {
            throw new SMSException(ex.getMessage());
        }
    }

}
