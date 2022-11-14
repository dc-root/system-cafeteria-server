package br.com.diego.Lanchonete.address.utils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
        .addMapping("/**")
        .allowedOrigins("http://localhost:3000", "http://localhost:5500")
        .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}
