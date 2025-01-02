package com.myshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.Map;

//@Component
//public class RequestMappingLogger implements ApplicationListener<ContextRefreshedEvent> {

//    private static final Logger LOGGER = LoggerFactory.getLogger(RequestMappingLogger.class);

//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        ApplicationContext applicationContext = event.getApplicationContext();
//        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
//                .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
//        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
//        map.forEach((key, value) -> LOGGER.info("{} {}", key, value));
//    }
//}
