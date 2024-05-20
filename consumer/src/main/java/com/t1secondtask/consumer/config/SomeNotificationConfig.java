package com.t1secondtask.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SomeNotificationConfig {

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
