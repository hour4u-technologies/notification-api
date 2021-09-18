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

package com.codemiro.hour4u.notificationservice.broker;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

/**
 * The type Config mq.
 */
@Configuration
@EnableRabbit
public class ConfigMq {

    /**
     * The Mq properties.
     */
    @Autowired
    MqProperties mqProperties;

    /**
     * Rabbit listener container factory simple rabbit listener container factory.
     *
     * @param connectionFactory the connection factory
     * @return the simple rabbit listener container factory
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDefaultRequeueRejected(false);
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        messageConverter.getJavaTypeMapper().addTrustedPackages("*");
        factory.setMessageConverter(messageConverter);
        factory.setErrorHandler(errorHandler());
        //factory.setContainerCustomizer(container -> /* customize the container */);
        return factory;
    }

    /**
     * Message converter jackson 2 json message converter.
     *
     * @return the jackson 2 json message converter
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Error handler error handler.
     *
     * @return the error handler
     */
    @Bean
    public ErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(new MyFatalExceptionStrategy());
    }

    /**
     * The type My fatal exception strategy.
     */
    public static class MyFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {

        @Override
        public boolean isFatal(Throwable t) {
            if (t instanceof ListenerExecutionFailedException) {
                ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
                logger.error("Failed to process inbound message from queue "
                        + lefe.getFailedMessage().getMessageProperties().getConsumerQueue()
                        + "; failed message: " + lefe.getFailedMessage(), t);
            }
            return super.isFatal(t);
        }

    }

    /**
     * Q job seeker mail queue.
     *
     * @return the queue
     */
    @Bean
    public Queue qJobSeekerMail() {
        return new Queue("Q_JOB_SEEKER_MAIL", false);
    }

    /**
     * Q welcome mail queue.
     *
     * @return the queue
     */
    @Bean
    public Queue qWelcomeMail() {
        return new Queue("Q_WELCOME_MAIL", false);
    }

    /**
     * Q user mail queue.
     *
     * @return the queue
     */
    @Bean
    public Queue qUserMail() {
        return new Queue("Q_USER_MAIL", false);
    }

    /**
     * Q employment mail queue.
     *
     * @return the queue
     */
    @Bean
    public Queue qEmploymentMail() {
        return new Queue("Q_EMPLOYMENT_MAIL", false);
    }

    /**
     * Q auth sms queue.
     *
     * @return the queue
     */
    @Bean
    public Queue qAuthSMS() {
        return new Queue("Q_AUTH_SMS", false);
    }

    /**
     * Q notification queue.
     *
     * @return the queue
     */
    @Bean
    public Queue qNotification() {
        return new Queue("Q_WEB_NOTIFICATION", false);
    }

    /**
     * Q push notification queue.
     *
     * @return the queue
     */
    @Bean
    public Queue qPushNotification() {
        return new Queue("Q_PUSH_NOTIFICATION", false);
    }
}
