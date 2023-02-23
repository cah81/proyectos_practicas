package com.formacion.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {
//   @Bean
//  public RouteLocator configureRoute(RouteLocatorBuilder builder) {
//     return builder.routes()
//              .route("client", r->r.path("/client/**").uri("http://localhost:8200/client"))
//             .route("trip", r->r.path("/trip/**").uri("localhost:8200:/trip"))
//             .route("ticket", r->r.path("/ticket/**").uri("localhost:8200:/ticket"))
//              .build();
//   }
}
