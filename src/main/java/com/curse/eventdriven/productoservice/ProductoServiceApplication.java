package com.curse.eventdriven.productoservice;

import com.curse.eventdriven.productoservice.command.interceptor.CreateProductCommandInterceptor;
import com.curse.eventdriven.productoservice.core.error.ProductServiceEventsErrorHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductoServiceApplication.class, args);
    }

    @Autowired
    public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus bus) {
        bus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
    }

    @Autowired
    public void configure(EventProcessingConfigurer config){
        config.registerListenerInvocationErrorHandler("product-group",
                conf -> new ProductServiceEventsErrorHandler());
    }
}
