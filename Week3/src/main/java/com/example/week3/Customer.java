package com.example.week3;

public class Customer {
    private String ID;
    private String name;
    private String sex;
    private Integer age;

    public Customer() {
        this.ID = "";
        this.name = null;
        this.sex = "Female";
        this.age = 0;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if (age <0 ){
            this.age = 0;
        }
        else{
            this.age = age;
        }
    }

    public Customer(String ID, String name, String sex, Integer age) {
        this.ID = ID;
        this.name = name;
        this.sex = sex;
        this.age = age;
        if (age <0 ){
            age = 0;
        }

    }
}
