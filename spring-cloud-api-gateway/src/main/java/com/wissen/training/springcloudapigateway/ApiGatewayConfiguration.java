package com.wissen.training.springcloudapigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    private Logger logger = LoggerFactory.getLogger(ApiGatewayConfiguration.class);

    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        logger.info("Requests Going From Api Gateway");
        return routeLocatorBuilder.routes()
                        .route(p->p.path("/movies/**").uri("lb://booking-service")).build();
    }
}
