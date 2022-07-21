package com.curse.eventdriven.productoservice.core.mappers;

import com.curse.eventdriven.productoservice.core.data.ProductEntity;
import com.curse.eventdriven.productoservice.query.rest.ProductRestModel;
import org.springframework.beans.BeanUtils;


public class ProductMapperRest {

    private ProductMapperRest() {
    }

    public static ProductRestModel entityToModelRest(ProductEntity productEntity){
        ProductRestModel productRestModel = new ProductRestModel();
        BeanUtils.copyProperties(productEntity,productRestModel);
        return productRestModel;
    }
}
