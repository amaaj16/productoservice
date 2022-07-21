package com.curse.eventdriven.productoservice.query;

import com.curse.core.events.ProductReservedEvent;
import com.curse.eventdriven.productoservice.core.events.ProductCreatedEvent;
import com.curse.eventdriven.productoservice.core.data.ProductEntity;
import com.curse.eventdriven.productoservice.core.data.ProductRepository;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ProcessingGroup("product-group")
public class ProductsEventsHandler {

    private final ProductRepository productRepository;

    @ExceptionHandler()
    public void handler(Exception e) throws Exception {
        //Log Error Message
        throw e;
    }

    @ExceptionHandler(resultType = IllegalStateException.class)
    public void handler(IllegalStateException e) {
        //Log Error Message

    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductEntity productEntity = new ProductEntity();

        BeanUtils.copyProperties(event,productEntity);
        try {
            productRepository.save(productEntity);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }


    }
    @EventHandler
    public void on(ProductReservedEvent productReservedEvent){
        ProductEntity productEntity = productRepository.findByProductId(productReservedEvent.getProductId());
        productEntity.setQuantity(productEntity.getQuantity()-productReservedEvent.getQuantity());
        productRepository.save(productEntity);
    }
}
