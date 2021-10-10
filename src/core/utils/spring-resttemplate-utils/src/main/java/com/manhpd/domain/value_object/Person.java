package com.manhpd.domain.value_object;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long id;

    private Integer age;

    private String firstName;

    private String lastName;

}
