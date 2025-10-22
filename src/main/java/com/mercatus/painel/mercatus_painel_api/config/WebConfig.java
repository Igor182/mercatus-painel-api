package com.mercatus.painel.mercatus_painel_api.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings (CorsRegistry registry) {
        registry.addMapping("/api/**") // Permite CORS para todos os endpoints que começam com /api/
                .allowedOrigins("http://localhost:3000") // Permite requisições desta origem (seu front-end React)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Headers permitidos
                .allowCredentials(true);
    }


}
