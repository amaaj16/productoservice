package com.curse.eventdriven.productoservice.rest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRestModel {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
