package com.manhpd.domain.service;

import com.manhpd.domain.value_object.Person;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IPersonService {

    List<Person> getAllPerson();

    Person getById(Long id);

    HttpStatus addPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Long id);

}
