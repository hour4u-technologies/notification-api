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

package com.codemiro.hour4u.notificationservice.web.controller;

import com.codemiro.hour4u.notificationservice.domain.WebNotification;
import com.codemiro.hour4u.notificationservice.service.business.WebNotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Notification controller
 *
 * @author Sagar Baranwal
 * @version 1.0
 * @since 1.0
 */
@Api(value = "Notification API", tags = "Notification API")
@RestController
@RequestMapping("/v1/notification")
public class WebNotificationController {

    @Autowired
    private WebNotificationService service;

    /**
     * <p>Get all {@link WebNotification}</p>
     *
     * @param employerId the employer id
     * @return {@link WebNotification} by reference role
     */
    @ApiOperation(value = "Get Notifications", response = WebNotification.class)
    @GetMapping("/all")
    public List<WebNotification> getAll(@RequestParam(required = false) String employerId) {
        return service.getAll(employerId);
    }

    /**
     * <p>Mark all read {@link WebNotification} by reference role</p>
     *
     * @param referenceRole the reference role
     * @return {@link WebNotification} by reference role
     */
    @ApiOperation(value = "Mark all ready for admin", response = WebNotification.class)
    @PutMapping("/employer/{employerId}")
    public void markAllReadByAdmin(@PathVariable String referenceRole) {
         //service.markAllReadByReferenceRole(referenceRole);
    }
}
