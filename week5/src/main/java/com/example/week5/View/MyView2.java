package com.example.week5.View;

import ch.qos.logback.core.Layout;
import com.example.week5.Consumer.Sentence;
import com.example.week5.Publisher.Word;
import com.example.week5.Publisher.WordPublisher;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route("index2")
public class MyView2 extends HorizontalLayout {
//    private WordPublisher wp = new WordPublisher();

    private Button btn1, btn2, btn3 ,btn4;
    private VerticalLayout vl1, vl2;
    private TextField tfW, tfS;
    private TextArea taG, taB;
    private ComboBox<String> gw,bw;

    private Word word = new Word();
    private Notification notification = new Notification();


    public MyView2() {
        this.vl1 = new VerticalLayout();
        vl1.setWidth("100%");
        this.vl2 = new VerticalLayout();
        vl2.setWidth("100%");


        this.btn1 = new Button("Add Good Word");
        this.btn2 = new Button("Add Bad Word");
        this.btn3 = new Button("Add Sentence");
        this.btn4 = new Button("Show Sentence");
        this.gw = new ComboBox<>("Good Words");
        this.gw.setItems(word.goodWords);

        this.bw = new ComboBox<>("Bad Words");
        this.bw.setItems(word.badWords);


        this.tfW = new TextField("Add word", "");

        this.tfS = new TextField("Add Sentence");

        this.taG = new TextArea("Good Sentence");
        this.taB = new TextArea("Bad Sentence");
        vl1.add(tfW , btn1, btn2, gw, bw);
        vl2.add(tfS, btn3, taG, taB, btn4);
        this.add(vl1, vl2);
        btn1.addClickListener(event -> {
            ArrayList<String> item = WebClient.create().post().uri("http://localhost:8080/addGood/"+tfW.getValue()).retrieve().bodyToMono(new ParameterizedTypeReference<ArrayList<String>>() {
            }).block();

            gw.setItems(item);

        });

        btn2.addClickListener(event -> {
            ArrayList<String> item = WebClient.create().post().uri("http://localhost:8080/addBad/"+tfW.getValue()).retrieve().bodyToMono(new ParameterizedTypeReference<ArrayList<String>>() {
            }).block();

            bw.setItems(item);

        });

        btn3.addClickListener(event -> {
            String item = String.valueOf(WebClient.create().post().uri("http://localhost:8080/proof/"+tfS.getValue()).retrieve().bodyToMono(new ParameterizedTypeReference<ArrayList<String>>() {
            }).block());
            notification.open();
            notification.show("proof :" + item.toString());
            System.out.println(item);
            System.out.println(item.toString());
//            bw.setItems(item);

        });

        btn4.addClickListener(event -> {
            Sentence item = WebClient.create()
                .get()
                //แก้ตรงที่ตรูเอา ? ครอบไว้
                .uri("http://localhost:8080/getSentence ?/?")
                .retrieve()
                .bodyToMono(Sentence.class)
                .block();
//            Notification.show("proof :" + item.toString());
            System.out.println(item.goodSentences.toString());
            taG.setValue(item.goodSentences.toString());
            taB.setValue(item.badSentences.toString());


//            System.out.println(item.toString());
//            notification.open();
//            bw.setItems(item);

        });
        }
    }

