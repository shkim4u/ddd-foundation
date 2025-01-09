package com.myshop.springconfig.rabbitmq;

import org.springframework.cloud.function.context.converter.avro.AvroSchemaMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class RabbitMQConfig {
    @Bean
    public MessageChannel output() {
        return new DirectChannel();
    }

//    @Bean
//    public MessageConverter avroMessageConverter() {
//        return new AvroSchemaMessageConverter(MimeTypeUtils.APPLICATION_OCTET_STREAM);
//    }
}
