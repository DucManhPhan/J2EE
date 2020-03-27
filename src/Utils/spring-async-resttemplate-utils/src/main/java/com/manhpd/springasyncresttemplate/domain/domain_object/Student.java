package com.manhpd.springasyncresttemplate.domain.domain_object;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    private String name;

    private Integer age;

    private String address;

}
