package com.manhpd.dto;

public class StudentDto {

    private String name;

    private int age;

    public StudentDto() {
        // nothing to do
    }

    public StudentDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void doHomework() {
        System.out.println(this.name + " is doing his homework.");
    }

    public void workInLibrary() {
        System.out.println(this.name + " is learning at the library");
    }

}
