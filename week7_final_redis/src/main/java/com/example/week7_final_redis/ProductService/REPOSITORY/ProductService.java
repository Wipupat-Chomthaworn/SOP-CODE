package com.example.week7_final_redis.ProductService.REPOSITORY;
import com.example.week7_final_redis.ProductService.POJO.Product;
import com.example.week7_final_redis.ProductService.REPOSITORY.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public ProductService(ProductRepository productRepository){
        this.repository = productRepository;
    }
    @CacheEvict(value = "Product", allEntries = true)
    @RabbitListener(queues = "AddProductQueue")
    public Boolean addProduct(Product product){
        try{
            repository.save(product);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

//    @CachePut(value = "Product")
    @CacheEvict(value = "Product", allEntries = true)
    @RabbitListener(queues = "UpdateProductQueue")
    public Boolean updateProduct(Product product){
        try{
            repository.save(product);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    @CacheEvict(value = "Product", allEntries = true)
    @RabbitListener(queues = "DeleteProductQueue")
    public boolean deleteProduct(Product product){
        try{
            this.repository.delete(product);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Cacheable(value="Product", key="'Product'")
    @RabbitListener(queues = "GetAllProductQueue")
    public List<Product> getAllProduct(){
        try{

            return repository.findAll();
        }
        catch(Exception e){
            return null;
        }
    }
    @RabbitListener(queues = "GetNameProductQueue")
    public Product getProductByName(String productName){
        return repository.findByName(productName);
    }
//    serviceAddProduct()
//    serviceUpdateProduct
//    serviceDeleteProduct
}
