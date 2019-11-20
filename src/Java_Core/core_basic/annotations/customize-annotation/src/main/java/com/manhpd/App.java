package com.manhpd;


import com.manhpd.dto.Person;

/**
 * https://dzone.com/articles/create-your-own-constraint-with-bean-validation-20
 *
 */
public class App {

    public static void main(String[] args) {
        Person person = new Person("Obama", "New York", 20);
        person.setNumOfChildren(20);
        System.out.println(person.toString());
    }

}
