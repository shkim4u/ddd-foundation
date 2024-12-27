package com.myshop.order.infra.rest.feign;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class InventoryFeignConfig {
//    private final ObjectFactory<HttpMessageConverters> messageConverters;
//
//    public FeignConfig(ObjectFactory<HttpMessageConverters> messageConverters) {
//        this.messageConverters = messageConverters;
//    }
//
//    @Bean
//    public Decoder feignDecoder() {
//        return new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters)));
//    }
//
//    @Bean
//    public Encoder feignEncoder() {
//        return new SpringEncoder(messageConverters);
//    }
}
