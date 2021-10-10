package com.manhpd.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;

    private String address;

    private int age;

    @Override
    public String toString() {
        return "This is " + name + " in " + address + String.valueOf(age);
    }

}
