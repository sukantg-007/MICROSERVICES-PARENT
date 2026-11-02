package com.vc.orderservice.config;

import com.vc.orderservice.client.ClientConfigBuilder;
import com.vc.orderservice.client.ShowClientService;
import com.vc.orderservice.client.UserClientService;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class HttpServiceClientConfig {
    @Value("${show.service.base.url}")
    private String showServiceBaseUrl;

    @Value("${user.service.base.url}")
    private String userServiceBaseUrl;

    @LoadBalanced
    @Bean
    RestClient.Builder restClientBuilder(ObservationRegistry observationRegistry){
        return RestClient.builder()
                .observationRegistry(observationRegistry);
    }

    @Bean
    ShowClientService showClientService(RestClient.Builder restClientBuilder){
               ClientConfigBuilder<ShowClientService> showClientBuilder= ClientConfigBuilder.<ShowClientService>builder()
                       .baseUrl(showServiceBaseUrl)
                       .clientClass(ShowClientService.class).build();
               return showClientBuilder.build(restClientBuilder.clone());
    }

    @Bean
    UserClientService userClientService(RestClient.Builder restClientBuilder){
               ClientConfigBuilder<UserClientService> userClientBuilder= ClientConfigBuilder.<UserClientService>builder()
                       .baseUrl(userServiceBaseUrl)
                       .clientClass(UserClientService.class).build();
               return userClientBuilder.build(restClientBuilder.clone());
    }
}
