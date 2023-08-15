package com.example.week3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathService {
    @RequestMapping("/")
            public String sayHi(){
        return "Hi, me";
    }
//    sum
    @RequestMapping("/add/{fn}/{ln}")
    public Integer sum( @PathVariable("fn") Integer fn, @PathVariable("ln") Integer ln){
        return fn + ln;
    }
//    minus
@RequestMapping("/minus/{fn}/{ln}")
public String minus( @PathVariable("fn") Double fn, @PathVariable("ln") Double ln){
    return String.valueOf(fn - ln);
}
//multiply
@RequestMapping("/multiply")
public String multiply(@RequestParam("fn") Double fn, @RequestParam("ln") Double ln){
    return String.valueOf(fn * ln);
}
//devide
    @RequestMapping("/devide")
    public String devide(@RequestParam("fn") Double fn, @RequestParam("ln") Double ln){
        return String.valueOf(fn / ln);
    }
//    System.out.println("Hello world");

}
