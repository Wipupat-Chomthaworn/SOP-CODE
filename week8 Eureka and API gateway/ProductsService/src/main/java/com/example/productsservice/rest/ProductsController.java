package com.example.productsservice.rest;

import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.Put;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final Environment env;

    public ProductsController(Environment env){
        this.env = env;
    }

    @PostMapping
    public String createProduct(){
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
