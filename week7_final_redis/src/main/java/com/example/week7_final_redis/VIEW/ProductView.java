package com.example.week7_final_redis.VIEW;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("week7.2")
public class ProductView extends VerticalLayout {
    private ComboBox cbPL;
    private TextField tfPName;
    private NumberField tfPCost;
    private NumberField tfPProfit;
    private NumberField tfPPrice;
    private Button addP, upP, delP, clearP;
    private HorizontalLayout hl;
    private Notification notification;

    public ProductView() {
        this.cbPL = new ComboBox<>();
        this.tfPName = new TextField("Product Name", "");
        this.tfPCost = new NumberField("Product Cost");
        tfPCost.setValue(0.0);
        this.tfPProfit = new NumberField("Product Profit");
        tfPProfit.setValue(0.0);

        this.tfPPrice = new NumberField("Product Price");
        tfPPrice.setValue(0.0);

//        tfPPrice.setReadOnly(true);
        tfPPrice.setEnabled(false);
        this.addP = new Button("Add Product");
        this.upP = new Button("Update Product");
        this.delP = new Button("Del Product");
        this.clearP = new Button("Clear Product");

        hl = new HorizontalLayout();
        hl.add(addP, upP, delP, clearP);
        this.add(cbPL, tfPName, tfPCost, tfPProfit, tfPPrice, hl);


        clearP.addClickListener(event ->{
            tfPName.setValue("");
            tfPCost.setValue(0.0);
            tfPProfit.setValue(0.0);
            tfPPrice.setValue(0.0);
//            notification.open();
        });
        this.tfPCost.addKeyPressListener(Key.ENTER, e -> {});
    }
    public void getPrice() {
        Double cost = this.pCost.getValue();
        Double profit = this.pProfit.getValue();
        Double out = WebClient.create().get().uri("http://localhost:8080/getPrice/" + cost + "/" + profit).retrieve().bodyToMono(Double.class).block();
        this.pPrice.setValue(out);
    }


}
