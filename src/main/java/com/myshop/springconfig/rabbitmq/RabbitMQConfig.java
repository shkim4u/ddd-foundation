package com.myshop.springconfig.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class RabbitMQConfig {
    @Bean
    public MessageChannel output() {
        return new DirectChannel();
    }
}
