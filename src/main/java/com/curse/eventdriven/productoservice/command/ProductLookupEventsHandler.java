package com.curse.eventdriven.productoservice.command;

import com.curse.eventdriven.productoservice.core.data.ProductLookupEntity;
import com.curse.eventdriven.productoservice.core.data.ProductLookupRepository;
import com.curse.eventdriven.productoservice.core.events.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@AllArgsConstructor
public class ProductLookupEventsHandler {

    private ProductLookupRepository productLookupRepository;

    @EventHandler
    public void on(ProductCreatedEvent event){

        ProductLookupEntity productLookup = new ProductLookupEntity(event.getProductId(), event.getTitle());

        productLookupRepository.save(productLookup);

    }
}
