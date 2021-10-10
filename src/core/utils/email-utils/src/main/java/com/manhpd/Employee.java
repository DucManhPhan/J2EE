package com.manhpd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String firstName;

    private String lastName;

    private int monthlyIncome;

    private int nbHoursPerWeek;

    private String email;

}
