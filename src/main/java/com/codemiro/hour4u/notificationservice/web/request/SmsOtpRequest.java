package com.codemiro.hour4u.notificationservice.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Sms otp request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsOtpRequest {

    /**
     * The Sms otp.
     */
    String smsOtp;

    /**
     * The Phone no.
     */
    String phoneNo;
}
