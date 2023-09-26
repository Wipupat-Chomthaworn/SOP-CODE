package com.example.week6_repository_pattern_for_microservice.POJO;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

//POJO CLASS
@Data // เพื)อบง่ บอกว่าคลาสนีDเป็น Plain Old Java Object (POJO)
@Document("Wizard") // เพื)อกาํ หนด collection name ที)คลาสนีDจะเป็น Data Model
public class Wizard implements Serializable {
    @Id // ใช้บง่ บอกว่าแอททริบิวใดเป็นคีย์ และสร้างให้รองรบั ObjectId ของ MongoDB
    private String _id;
    private String sex;
    private String name;
    private String position;
    private int money;
    private String school;
    private String house;


    public Wizard() {
    }

    public Wizard(String _id, String sex, String name, String position, int money, String school, String house) {
        this._id = _id;
        this.sex = sex;
        this.name = name;
        this.position = position;
        this.money = money;
        this.school = school;
        this.house = house;


    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
