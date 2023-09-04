package com.example.week5;

import java.util.ArrayList;
import java.util.Arrays;

public class Word {
    public ArrayList<String> badWords;

    public ArrayList<String> goodWords;

    public Word() {
        this.badWords = new ArrayList<String>(
//                Arrays.asList(new Word("nice"), new Word("fuck"))
        );
        badWords.add("fuck");
        badWords.add("olo");

        this.goodWords = new ArrayList<String>();
        goodWords.add("happy");
        goodWords.add("enjoy");
        goodWords.add("life");
    }
}
