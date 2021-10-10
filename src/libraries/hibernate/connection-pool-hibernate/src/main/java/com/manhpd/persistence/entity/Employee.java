package com.manhpd.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Table(name = "employee")
@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FULL_NAME")
    private String name;

    @Column(name = "AGE")
    private int age;

    public Employee() {
        // nothing to do
    }

    public Employee(int id, String name, int age) {
        this.id   = id;
        this.name = name;
        this.age  = age;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age  = age;
    }

    @Override
    public String toString() {
        return "Employee: " + this.id + ", " + this.name + ", " + this.age;
    }
}
