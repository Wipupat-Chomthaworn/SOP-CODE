package com.example.week4.views;

import com.example.week4.Change;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "index2")
public class CashierView extends VerticalLayout {
    private TextField tfMoney, tfB1000, tfB500, tfB100, tfB20, tfB10, tfB5, tfB1;
    private Button calChangeBtn;

//    tfMoney= new TextField("tfMoney"); tfAns= new TextField("tfAns"); tfB1000= new TextField("tfB1000"); tfB500= new TextField("tfB500"); tfB100= new TextField("tfB100"); tfB20= new TextField("tfB20"); tfB10= new TextField("tfB10"); tfB5= new TextField("tfB5"); tfB1= new TextField("tfB1")


    public CashierView() {
        calChangeBtn = new Button("คำนวณเงินทอน");
        calChangeBtn.addClickListener(event -> {
            Change out = WebClient.create() // สร้างชองทางการสอสาร
                    .get() // กําหนดรูปแบบการสือสาร
                    .uri("http://localhost:8080/getChange/" + tfMoney.getValue())
                    .retrieve() // ให้รอรับข้อมูลกลับมา
                    .bodyToMono(Change.class)
                    .block();

//            set btn value back
            tfB1000.setValue(out.getB1000() + "");
            tfB500.setValue(out.getB500() + "");
            tfB100.setValue(out.getB100() + "");
            tfB20.setValue(out.getB20() + "");
            tfB10.setValue(out.getB10() + "");
            tfB5.setValue(out.getB5() + "");
            tfB1.setValue(out.getB1() + "");

        });

        tfMoney = new TextField("");
        tfB1000 = new TextField("");
        tfB500 = new TextField("");
        tfB100 = new TextField("");
        tfB20 = new TextField("");
        tfB10 = new TextField("");
        tfB5 = new TextField("");
        tfB1 = new TextField("");

        tfMoney.setPrefixComponent(new Div(new Text("$Money")));
        tfB1000.setPrefixComponent(new Div(new Text("$1000")));
        tfB500.setPrefixComponent(new Div(new Text("$500")));
        tfB100.setPrefixComponent(new Div(new Text("$100")));
        tfB20.setPrefixComponent(new Div(new Text("$20")));
        tfB10.setPrefixComponent(new Div(new Text("$10")));
        tfB5.setPrefixComponent(new Div(new Text("$5")));
        tfB1.setPrefixComponent(new Div(new Text("$1")));
        add(tfMoney,
                calChangeBtn,
                tfB1000,
                tfB500,
                tfB100,
                tfB20,
                tfB10,
                tfB5,
                tfB1);
    }

}
