package com.manhpd.domain.service;

import com.manhpd.domain.value_object.Person;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Override
    public List<Person> getAllPerson() {
        return null;
    }

    @Override
    public Person getById(Long id) {
        return null;
    }

    @Override
    public HttpStatus addPerson(Person person) {
        return null;
    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(Long id) {

    }
}
