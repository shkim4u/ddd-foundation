package com.myshop.order.infra.rest.feign;

import feign.FeignException;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class InventoryFeignConfig {
    /**
     * Debug log level
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    /**
     * Custom Header
     */
//    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json;charset=UTF-8");
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new InventoryErrorDecoder();
    }

    @Bean
    public Decoder feignDecoder() {
        return new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter())));
    }

    @Bean
    public SpringEncoder feignEncoder() {
        return new SpringEncoder(feignHttpMessageConverter());
    }

    private ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        return () -> new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
    }

    public static class InventoryErrorDecoder implements ErrorDecoder {
        private final ErrorDecoder defaultErrorDecoder = new Default();

        @Override
        public Exception decode(String methodKey, Response response) {
            if (response.status() == 404) {
                return new InventoryNotFoundException("Inventory API path not found");
            }
            return defaultErrorDecoder.decode(methodKey, response);
        }
    }

    public static class InventoryNotFoundException extends FeignException {
        public InventoryNotFoundException(String message) {
            super(404, message);
        }
    }

//    @Bean
//    public LoadBalancerClient loadBalancerClient() {
//        return new LoadBalancerClient() {
//            @Override
//            public ServiceInstance choose(String serviceId) {
//                return null;
//            }
//            // ... other methods
//        };
//    }
}
