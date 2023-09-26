package com.example.week7_final_redis.ProductService.REPOSITORY;

import com.example.week7_final_redis.ProductService.POJO.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(value = "{productName:'?0'}")
    public Product findByName(String productName);

}
