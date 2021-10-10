package com.manhpd.domain.service;

import com.manhpd.domain.value_object.Person;
import org.springframework.http.HttpStatus;

import java.util.Collection;

public interface IPersonService {

    Collection<Person> getAllPerson();

    Person getById(Long id);

    HttpStatus addPerson(Person person);

    boolean updatePerson(Long id, Person person);

    boolean deletePerson(Long id);

}
