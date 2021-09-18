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

package com.codemiro.hour4u.notificationservice.model;

import lombok.Getter;

/**
 * The enum Ses from.
 */
public enum SESFrom {

    /**
     * The Contact.
     */
// SUPPORT("support@hour4u.com", "Hour4u Support"),
    CONTACT("contact@hour4u.com", "Hour4u Support"),
    /**
     * The Test.
     */
    TEST("support@hour4u.com", "Testing only");

    @Getter
    private final String email;
    @Getter
    private final String name;

    SESFrom(String email, String name) {
        this.email =email;
        this.name = name;
    }
}
