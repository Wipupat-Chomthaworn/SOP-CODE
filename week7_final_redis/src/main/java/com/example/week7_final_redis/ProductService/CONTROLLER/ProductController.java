package com.example.week7_final_redis.ProductService.CONTROLLER;

import com.example.week7_final_redis.ProductService.POJO.Product;
import com.example.week7_final_redis.ProductService.REPOSITORY.ProductService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ProductController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private ProductService

    @RequestMapping(value = "/addProduct", method = POST)
    public boolean addProduct(@RequestBody Product product){
        return (boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "add", product);

    }
    @RequestMapping(value = "/updateProduct", method = POST)
    public boolean updateProduct(@RequestBody Product product){
        return (boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "update", product);

    }
    @RequestMapping(value = "/deleteProduct", method = POST)
    public boolean deleteProduct(@RequestBody Product product){
        return (boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "delete", product);

    }
    @RequestMapping(value = "/getProductByName/{productName}")
    public Product getProductByName(@PathVariable String productName){
        return (Product) rabbitTemplate.convertSendAndReceive("ProductExchange", "getname", productName);
    }
    @RequestMapping(value = "/getProductAll")
    public List<Product> getProductAll(){
        return (List<Product>) rabbitTemplate.convertSendAndReceive("ProductExchange", "getall","");
    }
}
