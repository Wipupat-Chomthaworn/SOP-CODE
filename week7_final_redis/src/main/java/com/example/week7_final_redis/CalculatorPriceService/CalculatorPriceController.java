package com.example.week7_final_redis.CalculatorPriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorPriceController {

    @Autowired
    private CalculatorPriceService calPriceService;

    @RequestMapping(value = "/getPrice/{cost}/{profit}")
    public Double serviceGetProducts(@PathVariable Double cost, @PathVariable Double profit){
        return calPriceService.getPrice(cost, profit);
    }
}
