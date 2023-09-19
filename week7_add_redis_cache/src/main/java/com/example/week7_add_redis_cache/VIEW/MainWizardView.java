package com.example.week7_add_redis_cache.VIEW;

import com.example.week7_add_redis_cache.POJO.Wizard;
import com.example.week7_add_redis_cache.POJO.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Route("/mainPage.it")
public class MainWizardView extends VerticalLayout {
    private int index;
    private Wizards wizardList;
    private TextField tfName, tfDollar;
    private RadioButtonGroup gender;
    private ComboBox cbPosition, cbSchool, cbHouse;
    private RadioButtonGroup<String> radioButtonGroup;
    private Button btnLeft, btnRight, btnCreate, btnUpdate, btnDelete;
    //    List<Wizard> wizardList = new ArrayList<Wizard>();
    private HorizontalLayout Hl = new HorizontalLayout();
    private Notification notification;


    public MainWizardView() {
        index = 0;
        wizardList = new Wizards();
        tfName = new TextField();
        tfName.setPlaceholder("Full name");

//        gender.setLabel("Gender : ");
        gender = new RadioButtonGroup<>("Gender : ", "m", "f");


        cbPosition = new ComboBox();
        cbPosition.setItems("Student", "Teacher");
        cbPosition.setPlaceholder("Position");

        tfDollar = new TextField();
        tfDollar.setPrefixComponent(new Span("$"));


        cbSchool = new ComboBox();
        cbSchool.setLabel("School");
        cbSchool.setItems("Hogwarts", "Beauxbatons", "Durmstrang");

        cbHouse = new ComboBox();
        cbHouse.setLabel("House");
        cbHouse.setItems("Gryffindor", "Ravenclaw", " Hufflepuff", "Slyther");

        btnLeft = new Button("<<");
        btnCreate = new Button("Create");
        btnUpdate = new Button("Update");
        btnDelete = new Button("Delete");
        btnRight = new Button(">>");
        notification = new Notification();

        Hl.add(btnLeft, btnCreate, btnUpdate, btnDelete, btnRight);
        this.add(tfName, gender, cbPosition, tfDollar, cbSchool, cbHouse, Hl);
        loadWizards();

        //add Listener
        btnRight.addClickListener(event -> {
            int lengthList = wizardList.getModel().toArray().length;
            if (index + 1 < lengthList) {
                index++;
                setTextField(index);

            }
        });
        btnLeft.addClickListener(event -> {
            if (index - 1 >= 0) {
                index--;
                setTextField(index);
            }
        });
        btnCreate.addClickListener(event -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            String fullname = this.tfName.getValue();
            String sex = this.gender.getValue().toString();
            String position = this.cbPosition.getValue().toString();
            String dollar = this.tfDollar.getValue();
            String school = this.cbSchool.getValue().toString();
            String house = this.cbHouse.getValue().toString();
            formData.add("fullname", fullname);
            formData.add("sex", sex);
            formData.add("position", position);
            formData.add("dollar", dollar);
            formData.add("school", school);
            formData.add("house", house);

            Wizard wizardReturn = WebClient.create().post()
                    .uri("http://localhost:8080/addWizard").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(Wizard.class).block();
            index = this.wizardList.getModel().toArray().length;
//            index = wizardList.size();
            loadWizards();
            notification.setText((wizardReturn != null ? "Created" : "something went wrong"));
            notification.open();
        });
        btnUpdate.addClickListener(event -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            String id = this.wizardList.getModel().get(index).get_id();
            String fullname = this.tfName.getValue();
            String sex = this.gender.getValue().toString();
            String position = this.cbPosition.getValue().toString();
            String dollar = this.tfDollar.getValue();
            String school = this.cbSchool.getValue().toString();
            String house = this.cbHouse.getValue().toString();
            formData.add("id", id);
            formData.add("fullname", fullname);
            formData.add("sex", sex);
            formData.add("position", position);
            formData.add("dollar", dollar);
            formData.add("school", school);
            formData.add("house", house);

            Wizard wizardReturn = WebClient.create().post()
                    .uri("http://localhost:8080/updateWizard").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(Wizard.class).block();

            notification.setText((wizardReturn != null ? "Updated" : "something went wrong"));
            notification.open();
            loadWizards();
        });
        btnDelete.addClickListener(event -> {
            String status = WebClient.create().post().uri("http://localhost:8080/deleteWizard/" + wizardList.getModel().get(index).get_id()).retrieve().bodyToMono(String.class).block();
            notification.setText((status.equals("something went wrong")) ? "something went wrong" : status);
            notification.open();
            if (index - 1 >= 0) {
                index--;
            }
            loadWizards();
        });
    }

    public void loadWizards() {
        List<Wizard> out = WebClient.create().get()
                .uri("http://localhost:8080/wizards")
                .retrieve().bodyToFlux(Wizard.class).collectList().block();
//        System.out.println(wizardList);
        this.wizardList.setModel((ArrayList<Wizard>) out);
        setTextField(index);
    }
    public void setTextField(int index) {
        if(this.wizardList.getModel().isEmpty()){
            this.tfName.clear();
            this.gender.clear();
            this.cbPosition.clear();
            this.tfDollar.clear();
            this.cbSchool.clear();
            this.cbHouse.clear();
        }else{
            this.tfName.setValue(this.wizardList.getModel().get(index).getName());
            this.gender.setValue(this.wizardList.getModel().get(index).getSex());
            this.cbPosition.setValue(this.wizardList.getModel().get(index).getPosition());
            this.tfDollar.setValue(this.wizardList.getModel().get(index).getMoney() + "");
            this.cbSchool.setValue(this.wizardList.getModel().get(index).getSchool());
            this.cbHouse.setValue(this.wizardList.getModel().get(index).getHouse());
        }
    }

}
