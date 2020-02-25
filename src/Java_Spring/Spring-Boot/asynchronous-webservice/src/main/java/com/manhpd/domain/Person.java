package com.manhpd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Integer id;

    private Integer age;

    private String firstName;

    private String lastName;

}
