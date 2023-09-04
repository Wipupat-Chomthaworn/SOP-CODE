package com.example.week4.services;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

public class MathAPI {
    @RequestMapping("/plus/{n1}/{n2}")
    public Double myPlus(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return n1 + n2;

    }

    @RequestMapping("/minus/{n1}/{n2}")
    public Double myMinus(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return n1 - n2;

    }

    @RequestMapping("/divide/{n1}/{n2}")
    public Double myDivide(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return n1 / n2;

    }


    @RequestMapping("/multi/{n1}/{n2}")
    public Double myMulti(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return n1 * n2;

    }

    @GetMapping("/mod/{n1}/{n2}")
    public Double myMod(@PathVariable("n1") Double n1, @PathVariable("n2") Double n2) {
        return n1 % n2;

    }

    @RequestMapping(value = "/max", method = RequestMethod.POST)
    public Double myMax(@RequestBody MultiValueMap<String, String> mNum) {
        Map<String, String> v = mNum.toSingleValueMap();

        return Math.max(Double.parseDouble(v.get("n1")), Double.parseDouble(v.get("n2")));
//        n1 ,n2 ได้มาจาก cilent ส่งชื่อไรมา (ชื่อต้องตรง)


    }
}
