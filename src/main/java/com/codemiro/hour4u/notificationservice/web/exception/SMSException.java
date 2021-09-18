/*
 *
 *  Copyright 2018, 2019  Codemiro Technologies Pvt Ltd
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and  limitations under the License.
 *
 */

package com.codemiro.hour4u.notificationservice.web.exception;


/**
 * The type Sms exception.
 */
public class SMSException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -4924019017314751221L;

    /**
     * Instantiates a new Sms exception.
     *
     * @param msg the msg
     */
    public SMSException(String msg) {
        super(msg);
    }
}
