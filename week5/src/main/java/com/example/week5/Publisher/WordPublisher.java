package com.example.week5.Publisher;

import com.example.week5.Consumer.Sentence;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
// this is controller
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    protected Word words;

    public WordPublisher() {
        this.words = new Word();
    }

    @RequestMapping(value = "/addBad/{word}", method = RequestMethod.POST)
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

    @PostMapping("/addGood/{word}")
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

    @GetMapping("/getSentence")
    public Sentence getSentence() {
        return (Sentence) rabbitTemplate.convertSendAndReceive("Direct", "get", "");

    }

    ;

    @PostMapping("/proof/{sentence}")
    public String proofSentence(@PathVariable("sentence") String s
//    @PathVariable("n1") Double n1, @PathVariable("n2") Double n2
    ) {
        Boolean good = false;
        Boolean bad = false;

        for (String word : words.goodWords) {
            if (s.contains(word)) {
                good = true;
                System.out.println("good set");
            }
        }
        for (String word : words.badWords) {
            if (s.contains(word)) {
                bad = true;
                System.out.println("bad set");
            }
        }
//        words.badWords.contains(s)? rabbitTemplate.convertAndSend("Direct","",s):words.goodWords.contains(s)? rabbitTemplate.convertAndSend("Direct","",s):
        if (good & bad) {
            rabbitTemplate.convertAndSend("Fanout", "", s);
            System.out.println("fanout : ");
            System.out.println("[" + s + "]");
            return "Found good and bad";
        } else if (good) {
            rabbitTemplate.convertAndSend("Direct", "good", s);
            System.out.print("good direct : ");
            System.out.println("[" + s + "]");
            return "Found good";

        } else if (bad) {
            rabbitTemplate.convertAndSend("Direct", "bad", s);
            System.out.print("bad direct : ");
            System.out.println("[" + s + "]");

            return "Found bad";
        }
        {
            System.out.print("proof nothing : ");
            System.out.println("[" + s + "]");
            return "Found nothing";
        }
    }
}
