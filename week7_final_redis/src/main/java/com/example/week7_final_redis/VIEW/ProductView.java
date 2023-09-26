package com.example.week7_final_redis.VIEW;

import com.example.week7_final_redis.ProductService.POJO.Product;
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
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

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
        //to Press enter
        this.tfPCost.addKeyPressListener(Key.ENTER, e -> {
            getPrice();
        });
        this.tfPProfit.addKeyPressListener(Key.ENTER, e -> {
            getPrice();
        });
    }
    public void getPrice() {
        Double cost = this.tfPCost.getValue();
        
        Double out = WebClient.create().get().uri("http://localhost:8080/getPrice/" + cost + "/" + profit).retrieve().bodyToMono(Double.class).block();
        this.tfPPrice.setValue(out);
    }
    public void addProduct() {
        getPrice();
        Product newProduct = new Product(null, tfPName.getValue(), this.tfPCost.getValue(), this.tfPProfit.getValue(), this.tfPPrice.getValue());
        boolean status = WebClient.create().post().uri("http://localhost:8080/addProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newProduct)
                .retrieve().bodyToMono(boolean.class).block();
        notification.setText(status ? "Added" : "something not found");
        notification.open();
        loadProduct();
//        clearInput();
    }
    public void updateProduct() {
        getPrice();
        Product newProduct = new Product(this.product.get_id(), this.tfPName.getValue(), this.tfPCost.getValue(), this.tfPProfit.getValue(), this.tfPPrice.getValue());
        boolean status = WebClient.create().post().uri("http://localhost:8080/updateProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newProduct)
                .retrieve().bodyToMono(boolean.class).block();
        notification.setText(status ? "Updated" : "something not found");
        notification.open();
        clearInput();
        loadProduct();
    }
    public void delProduct() {
        getPrice();
        Product newProduct = new Product(this.product.get_id(), this.tfPName.getValue(), this.tfPCost.getValue(), this.tfPProfit.getValue(), this.tfPPrice.getValue());
        boolean status = WebClient.create().post().uri("http://localhost:8080/deleteProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newProduct)
                .retrieve().bodyToMono(boolean.class).block();
        notification.setText(status ? "Deleted" : "something not found");
        notification.open();
        loadProduct();
        clearInput();
    }

    public void clearInput() {
        setText("", 0.0, 0.0, 0.0);

    }

    public void setText(String name, Double tfPCost, Double tfPProfit, Double tfPPrice) {
        this.tfPName.setValue(name);
        this.tfPCost.setValue(ProductView.this.tfPCost);
        this.tfPProfit.setValue(ProductView.this.tfPProfit);
        this.tfPPrice.setValue(ProductView.this.tfPPrice);
    }

    public void loadProduct() {
        this.products = WebClient.create()
                .get()
                .uri("http://localhost:8080/getProductAll")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {
                })
                .block();
        updateComboBox();

    }
    public void updateComboBox() {
        List<String> productNames = this.products.stream()
                .map(Product::getProductName)
                .collect(Collectors.toList());
        this.cbPL.setItems(productNames);
    }


}
