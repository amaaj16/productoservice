package com.curse.eventdriven.productoservice.command.rest;

import com.curse.eventdriven.productoservice.command.CreateProductCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductCommandsController {

    private final Environment env;
    private final CommandGateway commandGateway;

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .title(createProductRestModel.getTitle())
                .productId(UUID.randomUUID().toString()).build();
        String returnValue;

        returnValue = commandGateway.sendAndWait(createProductCommand);

        return returnValue;
    }



    @PutMapping
    public String updatePrdProduct() {
        return "HTTP PUT HANDLED";
    }

    @DeleteMapping
    public String deletePrdProduct() {
        return "HTTP DELETE HANDLED";
    }
}
