package com.example.week5.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class SentenceConsumer implements Serializable {
    protected Sentence sentences;

    public SentenceConsumer() {
        this.sentences = new Sentence();
    }

    @RabbitListener(queues = "BadWordQueue")
    public void addBadSentence(String s) {
        sentences.badSentences.add(s);
        for (String i : sentences.badSentences) {
            System.out.println("bad sen: " + i);
        }
    }

    @RabbitListener(queues = "GoodWordQueue")
    public void addGoodSentence(String s) {
        sentences.goodSentences.add(s);
        for (String i : sentences.goodSentences) {
            System.out.println("good sen: " + i);
        }
    }
}
