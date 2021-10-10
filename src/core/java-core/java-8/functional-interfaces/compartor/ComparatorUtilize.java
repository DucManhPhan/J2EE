package com.manhpd.compartor;


import java.util.function.Function;

/**
 * Use Comparator functional interface that is used to compare two objects
 *
 * @FunctionalInterface
 * public interface Comparator<T> {
 *     int compare(T t1, T t2);
 * }
 *
 * If the returned integer is positive then t1 > t2.
 * If it is negative them t1 < t2
 * If it is equal to 0, then t1 = t2
 *
 * A Comparator only depends on:
 * - the type of items it compares
 * - and a function to extract a key
 * It can be built from this key extractor
 * And then can be reversed.
 *
 */
public class ComparatorUtilize {

    public static void main(String[] args) {

        // 1st example
//        Comparator<String> byConstants = (x, y) -> {
//            return removeVowels(x).compareTo(removeVowels(y));
//        };
//
//        System.out.println(byConstants.compare("Larry", "Libby"));

        // 2nd example: compare two Person object
        Person mary = new Person("Mary", 28);
        Person john = new Person("John", 22);
        Person linda = new Person("Linda", 26);
        Person james = new Person("Hames", 32);
        Person jamesBis = new Person("James", 26);

        Function<Person, String> getName = p -> p.getName();
        Function<Person, Integer> getAge = p -> p.getAge();

        Comparator<Person> cmpName = Comparator.comparing(getName);
        Comparator<Person> cmpAge = Comparator.comparing(getAge);

        // Should not use this version
        Comparator<Person> cmp = cmpName.thenComparing(cmpAge);

        // Use this improved version
        Comparator<Person> cmpOther = Comparator.comparing(Person::getName)
                                                .thenComparing(Person::getAge);

        System.out.println("Mary > John : " + (cmp.compare(mary, john) > 0));
        System.out.println("John > James : " + (cmp.compare(john, james) > 0));
        System.out.println("James > James Bis : " + (cmp.compare(james, jamesBis) > 0));
    }

    public static String removeVowels(String s) {
        return s.replaceAll("[aeiou]", "");
    }

    private static class Person {

        private String name;

        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return this.age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person [name = " + name + ", age = " + age + "]";
        }

    }

}

