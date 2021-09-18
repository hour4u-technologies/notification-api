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

package com.codemiro.hour4u.notificationservice.config;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.pinpoint.AmazonPinpoint;
import com.amazonaws.services.pinpoint.AmazonPinpointClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * The type Aws configs.
 */
@Configuration
public class AWSConfigs {

    /**
     * The Credentials.
     */
    AWSCredentials credentials = new BasicAWSCredentials("AKIAYITDYGM7DLGIZKXZ", "D4qIsBdkmcXMZ6LpyY7d/9LPktTs4oC6vMh/D9PP");

    /**
     * Aws credentials provider aws credentials provider.
     *
     * @return the aws credentials provider
     */
    @Bean
    @Primary
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new DefaultAWSCredentialsProviderChain();
    }

    /**
     * Amazon pinpoint amazon pinpoint.
     *
     * @return the amazon pinpoint
     */
    @Bean
    public AmazonPinpoint amazonPinpoint() {
        return AmazonPinpointClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTH_1).build();
    }

}
