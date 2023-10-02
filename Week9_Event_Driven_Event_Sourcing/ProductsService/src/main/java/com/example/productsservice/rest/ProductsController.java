package com.example.productsservice.rest;

import com.example.productsservice.command.CreateProductCommand;
import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.Put;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
//import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final Environment env;
    private final CommandGateway commandGateway;

    @Autowired
    public ProductsController(Environment env, CommandGateway commandGateway){
        this.env = env;
        this.commandGateway = commandGateway;
    }

//    public ProductsController(Environment env){
//        this.env = env;
//    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel model){
        CreateProductCommand command = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .title(model.getTitle())
                .price(model.getPrice())
                .quantity(model.getQuantity())
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        } catch (Exception e) {
            result = e.getLocalizedMessage();
        }
        return result;


//        return "HTTP POST handled";
    }
    @GetMapping
    public String getProduct(){
        return "HTTP GET handled" + env.getProperty("local.server.port");
    }
    @PutMapping
    public String updateProduct(){
        return "HTTP PUT handled";
    }
    @DeleteMapping
    public String deleteProduct(){
        return "HTTP DELETE handled";
    }

}
