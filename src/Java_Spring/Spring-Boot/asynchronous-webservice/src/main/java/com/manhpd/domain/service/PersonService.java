package com.manhpd.domain.service;

import com.manhpd.domain.Person;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class PersonService implements IPersonService {

    private static Integer nextID = 1;

    private final static Map<Integer, Person> persons = new HashMap<>() {{
        put(nextID, new Person(nextID++, 25, "Lukaku", "Oregy"));
        put(nextID, new Person(nextID++, 20, "Barack", "Obama"));
        put(nextID, new Person(nextID++, 52, "Gate", "Bill"));
    }};

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Person> getPersonById(Integer id) throws InterruptedException {
        Person person = persons.get(id);
        Thread.sleep(10000);
        System.out.println("getPersonById() waited 1.5s");
        return CompletableFuture.completedFuture(person);
    }
}
