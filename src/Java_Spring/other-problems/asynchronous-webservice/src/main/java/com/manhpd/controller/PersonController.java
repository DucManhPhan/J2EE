package com.manhpd.controller;

import com.manhpd.domain.Person;
import com.manhpd.domain.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping(value = "/persons/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) throws ExecutionException, InterruptedException {
        CompletableFuture<Person> compPerson = this.personService.getPersonById(id);

        // Wait until they are all done
        System.out.println("getPerson() method is waiting to get result");
        CompletableFuture.allOf(compPerson).join();
        Person person = compPerson.get();

        System.out.println("Completely");
        return ResponseEntity.ok(person);
    }

}
