package com.curse.eventdriven.productoservice.query;

import com.curse.eventdriven.productoservice.core.data.ProductEntity;
import com.curse.eventdriven.productoservice.core.data.ProductRepository;
import com.curse.eventdriven.productoservice.core.mappers.ProductMapperRest;
import com.curse.eventdriven.productoservice.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductsQueryHandler {

    private final ProductRepository productRepository;

    public ProductsQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery findProductsQuery) {
        List<ProductEntity> storedProducts = productRepository.findAll();
        return storedProducts.stream()
                .map(ProductMapperRest::entityToModelRest)
                .collect(Collectors.toList());
    }
}
