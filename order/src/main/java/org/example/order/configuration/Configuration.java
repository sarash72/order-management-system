package org.example.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public RestClient restClient(){
        return RestClient.builder().baseUrl("http://localhost:8082/api/products").build();
    }

    @Bean
    public WebClient webClientBean(){
        return WebClient.builder().build();
    }
}
