package com.manhpd.distlockingredis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private int id;

    private String name;

    private String lastName;

    private int age;

}
