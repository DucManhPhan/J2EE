package com.manhpd.usingvavr.exceptionHandling.firstExample;

import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.stream.Stream;

public class MockEmployeeRepository {

    private Employee[] employees;

    public MockEmployeeRepository() {
        this.employees = new Employee[]{
            new Employee(1, "Jane"),
            new Employee(2, "Liam")
        };
    }

    public Either<String, Employee> getById(int id) {
        return Option.ofOptional(Stream.of(this.employees)
                .filter(client -> id == client.getId())
                .findAny())
                .toEither("No Employee found");
    }

}
