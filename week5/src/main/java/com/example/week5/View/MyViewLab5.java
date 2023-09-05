package com.example.week5.View;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("index1")
public class MyViewLab5 extends HorizontalLayout {
    private Button btn;

    public MyViewLab5() {
        this.btn = new Button();
        this.add(btn);
    }
}
