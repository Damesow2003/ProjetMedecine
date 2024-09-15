package com.projetMedecine.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.addAllowedMethod("*");
            corsConfiguration.addAllowedOrigin("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**",corsConfiguration);

            return source;
    }
}
