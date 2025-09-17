package com.basic.postgres.client.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames(
                "classpath:messages/cliente/cliente_sucesso_pt",
                "classpath:messages/cliente/cliente_erro_pt",
                "classpath:messages/others/api_exceptions_pt"
        );
        return messageSource;
    }
}
