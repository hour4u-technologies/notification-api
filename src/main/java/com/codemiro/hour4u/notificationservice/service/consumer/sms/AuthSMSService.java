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

package com.codemiro.hour4u.notificationservice.service.consumer.sms;

import com.amazonaws.services.pinpoint.model.MessageType;
import com.codemiro.hour4u.notificationservice.constants.SMSConstants;
import com.codemiro.hour4u.notificationservice.model.OtpLoginRequest;
import com.codemiro.hour4u.notificationservice.util.SMSUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Auth sms service.
 */
@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RabbitListener(queues = "Q_AUTH_SMS")
public class AuthSMSService {

    private final SMSUtility smsUtility;

    /**
     * Send login otp.
     *
     * @param smsOtpRequest the sms otp request
     */
    @RabbitHandler
    public void sendLoginOtp(OtpLoginRequest smsOtpRequest) {
        String text = SMSConstants.LOGIN_OTP
                .replace(SMSConstants.OTP, smsOtpRequest.getOtp());
        smsUtility.sendSMSMessage(text, smsOtpRequest.getMobile(), MessageType.TRANSACTIONAL);
    }


}
