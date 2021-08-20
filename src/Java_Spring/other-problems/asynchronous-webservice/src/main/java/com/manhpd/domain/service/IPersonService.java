package com.manhpd.domain.service;

import com.manhpd.domain.Person;

import java.util.concurrent.CompletableFuture;

public interface IPersonService {

    CompletableFuture<Person> getPersonById(Integer id) throws InterruptedException;

}
