package com.curse.eventdriven.productoservice.command.rest;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class CreateProductRestModel {

    //@NotBlank(message = "Product tittle is a required field")
    private String title;

    @Min(value = 1,message = "Price can not be lower than 1")
    private BigDecimal price;

    @Min(value = 1,message = "Quantity can not be lower than 1")
    @Max(value = 100,message = "Price can not be larger than 5")
    private Integer quantity;
}
