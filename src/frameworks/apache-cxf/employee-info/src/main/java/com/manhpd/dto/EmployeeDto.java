package com.manhpd.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {

    private long id;

    private String name;

    private int age;

    private LocalDate birthday;

}
