package com.example.week7_final_redis.CalculatorPriceService;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class CalculatorPriceService {
    public double getPrice(double ProductCost,double ProductProfit){
        return ProductCost + ProductProfit;
    }

}
