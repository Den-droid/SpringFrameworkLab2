package com.example.lab2.config;

import com.example.lab2.services.interfaces.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Configuration
public class AppConfig {
    @Autowired
    private ServiceFactory serviceFactory;
    private final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    // To demonstrate prototype
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public LocalDateTime getCurrentDateTime() {
        logger.info("Prototype date time is created");
        return LocalDateTime.now();
    }
    // To demonstrate singleton
    @Bean
    public ServiceFactory getServiceFactory() {
        logger.info("Singleton Service Factory is created");
        return serviceFactory;
    }
}
