package com.example.week7_final_redis.ProductService.POJO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
//implement Serializable เพื่อ mapping ง่ายขึ้น
@Data
@Document("Product")
public class Product implements Serializable {
    @Id
    private String _id;
    private String productName;
    private double productCost;
    private double productProfit;
    private double productPrice;

}
