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
package com.codemiro.hour4u.notificationservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Abstract document.
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    @CreatedDate
    private DateTime createdOn;

    @LastModifiedDate
    private DateTime updatedOn;

    private Boolean isDelete;

    private Boolean deleteBy;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id.toString();
    }
}
