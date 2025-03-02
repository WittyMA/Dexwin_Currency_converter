/*
 * This class is a configuration class that defines beans for the Spring application context.
 * It is annotated with @Configuration to indicate that it contains @Bean definitions.
 */
package com.dexwin.currencyconverter.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for setting up application beans.
 * In this case, it configures a RestTemplate bean.
 *
 * @author yesulikplimits
 */
@Configuration
public class RestTemplateConfig {

    // Injecting RestTemplateBuilder to customize the RestTemplate instance.
    private final RestTemplateBuilder restTemplateBuilder;

    // Constructor injection of RestTemplateBuilder.
    public RestTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    /**
     * Creates and configures a RestTemplate bean using the injected RestTemplateBuilder.
     * The RestTemplate can be used to make RESTful web service calls.
     *
     * @return a configured RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        // Building and returning a RestTemplate instance.
        return restTemplateBuilder.build();
    }
}
