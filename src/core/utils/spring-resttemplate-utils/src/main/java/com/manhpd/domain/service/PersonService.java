package com.manhpd.domain.service;

import com.manhpd.domain.value_object.Person;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService implements IPersonService {

    private static Long nextID = 1L;

    private final static Map<Long, Person> persons = new HashMap<>() {{
        put(nextID, new Person(nextID++, 25, "John", "Doe"));
        put(nextID, new Person(nextID++, 20, "Mary", "Jackson"));
        put(nextID, new Person(nextID++, 21, "Peter", "Grey"));
        put(nextID, new Person(nextID++, 35, "Max", "Simpson"));
        put(nextID, new Person(nextID++, 16, "Lisa", "O'Melly"));
        put(nextID, new Person(nextID++, 20, "Josephine", "Rose"));
    }};

    @Override
    public Collection<Person> getAllPerson() {
        return persons.values();
    }

    @Override
    public Person getById(Long id) {
        return persons.get(id);
    }

    @Override
    public HttpStatus addPerson(Person person) {
        person.setId(++nextID);
        persons.put(person.getId(), person);

        return HttpStatus.CREATED;
    }

    @Override
    public boolean updatePerson(Long id, Person person) {
        if (!persons.containsKey(id)) {
            System.out.println("Id of this person does not exist in database");
            return false;
        }

        Person updatedPerson = persons.get(id);
        updatedPerson.setAge(person.getAge());
        updatedPerson.setFirstName(person.getFirstName());
        updatedPerson.setLastName(person.getLastName());

        return true;
    }

    @Override
    public boolean deletePerson(Long id) {
        Person person = persons.remove(id);
        return Objects.nonNull(person) ? true : false;
    }

    private List<Person> calculatePersonSubList(int page, int pageSize) {
        List<Person> PersonList = new ArrayList<>(persons.values());

        int startIndex = page * pageSize - pageSize;
        int endIndex = Math.min(page * pageSize, persons.size());

        try {
            return PersonList.subList(startIndex, endIndex);
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
