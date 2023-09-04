package com.example.week5;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    protected Word words;

    public WordPublisher() {
        this.words = new Word();
    }

    @RequestMapping(value = "/addBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> addBadWord(@PathVariable("word") String s) {
        words.badWords.add(s);
        System.out.println("add badword");
        return words.badWords;
    }

    @RequestMapping("/delBad/{word}")
    public ArrayList<String> deleteBadWord(@PathVariable("word") String s) {
        words.badWords.remove(s);
        System.out.println("del badword");
        return words.badWords;
    }

    @RequestMapping("/addGood/{word}")
    public ArrayList<String> addGoodWord(@PathVariable("word") String s) {
        words.goodWords.add(s);
        System.out.println("add goodword");
        return words.goodWords;
    }

    @RequestMapping("/delGood/{word}")
    public ArrayList<String> deleteGoodWord(@PathVariable("word") String s) {
        words.goodWords.remove(s);
        System.out.println("del goodword");
        return words.goodWords;
    }

    @GetMapping("/proof/{sentence}")
    public void proofSentence(@PathVariable("sentence") String s
//    @PathVariable("n1") Double n1, @PathVariable("n2") Double n2
    ) {
//        words.badWords.contains(s)? rabbitTemplate.convertAndSend("Direct","",s):words.goodWords.contains(s)? rabbitTemplate.convertAndSend("Direct","",s):
        if (words.goodWords.contains(s)) {
            rabbitTemplate.convertAndSend("Direct", "good", s);
            System.out.println("good direct");
        }

    else if (words.badWords.contains(s)){
        rabbitTemplate.convertAndSend("Direct", "bad", s);
            System.out.println("bad direct");


    }
    else if ((words.badWords.contains(s))& (words.goodWords.indexOf(s)!=-1)) {
            rabbitTemplate.convertAndSend("Fanout", "", s);
            System.out.println("fanout");
        }
    else {
            System.out.println("proof nothing");
        }
}
}
