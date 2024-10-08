package com.manhpd.usingvavr.exceptionHandling.firstExample;

import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.stream.Stream;

public class MockClientRepository {

    private Client[] clients;

    public MockClientRepository() {
        this.clients = new Client[] {
            new Client(1, "Jim", 1),
            new Client(2, "John", 1)
        };
    }

    public Either<String, Client> getById(int id) {
        return Option.ofOptional(Stream.of(clients)
                    .filter(client -> id == client.getId())
                    .findAny())
                .toEither("No client found");
    }

}
