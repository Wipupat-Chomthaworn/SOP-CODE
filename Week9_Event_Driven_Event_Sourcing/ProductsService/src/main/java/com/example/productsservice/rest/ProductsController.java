package com.example.productsservice.rest;

import com.example.productsservice.command.CreateProductCommand;
import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.Put;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final Environment env;
    private final CommandGateway commandGateway;
    @Autowired
    public ProductController(Environment env, CommandGateway commandGateway){
        this.env = env;
        this.commandGateway;


    }

    public ProductsController(Environment env){
        this.env = env;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestMode model){
        CreateProductCommand command = CreateProductCommand.builder()
                .productId(UUID.radomUUID().toString())
                .title(model.getTitle())


        return "HTTP POST handled";
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
