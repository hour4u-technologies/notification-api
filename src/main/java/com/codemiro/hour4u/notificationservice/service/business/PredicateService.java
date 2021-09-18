/*
 *
 *  Copyright 2020  Codemiro Technologies Pvt Ltd
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
package com.codemiro.hour4u.notificationservice.service.business;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslBindingsFactory;
import org.springframework.data.querydsl.binding.QuerydslPredicateBuilder;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

/**
 * The type Predicate service.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PredicateService {

    private final QuerydslPredicateBuilder querydslPredicateBuilder;
    private final QuerydslBindingsFactory querydslBindingsFactory;

    /**
     * Gets predicate from parameters.
     *
     * @param <T>        the type parameter
     * @param parameters the parameters
     * @param tClass     the t class
     * @return the predicate from parameters
     */
    public <T> Predicate getPredicateFromParameters(final MultiValueMap<String, String> parameters, Class<T> tClass) {
        TypeInformation<T> typeInformation = ClassTypeInformation.from(tClass);
        return querydslPredicateBuilder.getPredicate(
                typeInformation,
                parameters,
                querydslBindingsFactory.createBindingsFor(typeInformation));
    }
}
