package com.manhpd.model;

public class Person {

    private String name;

    private int age;

    private String city;

    public Person() {
        // nothing to do
    }

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person [name=" + this.name + ", age=" + this.age + ", city=" + this.city + "]";
    }

}
