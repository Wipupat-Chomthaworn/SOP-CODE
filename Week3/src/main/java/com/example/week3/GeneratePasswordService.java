package com.example.week3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.lang.Math;
@RestController
public class GeneratePasswordService {
//    gen password
        @RequestMapping("/{username:[a-z]+}.generate")
        public String genPass( @PathVariable("username") String username){
            int password = (int)(Math.random() * 100000000 - 99999999) * -1;
            return  "Hi, " + username + " \n Your new password is "+ password + ".";
        }
        //    sum




}
