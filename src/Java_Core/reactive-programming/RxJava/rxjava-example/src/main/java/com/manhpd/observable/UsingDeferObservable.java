package com.manhpd.observable;

import io.reactivex.rxjava3.core.Observable;

public class UsingDeferObservable {

    public static void main(String[] args) {
//        notUsingDefer();
        usingDefer();
    }

    public static void notUsingDefer() {
        final Person person = new Person();

        Observable<String> nameObservable = Observable.just(person.getName());
        Observable<Integer> ageObservable = Observable.just(person.getAge());

        person.setAge(35);
        person.setName("Bob");

        ageObservable.subscribe(age -> System.out.println("Age: " + age));
        nameObservable.subscribe(name -> System.out.println("Name: " + name));
    }

    public static void usingDefer() {
        final Person person = new Person();

        Observable<String> nameObservable = Observable.defer(() -> Observable.just(person.getName()));
        Observable<Integer> ageObservable = Observable.defer(() -> Observable.just(person.getAge()));

        person.setAge(35);
        person.setName("Bob");

        ageObservable.subscribe(age -> System.out.println("Age: " + age));
        nameObservable.subscribe(name -> System.out.println("Name: " + name));
    }

    private static class Person {
        private String name;
        private int age;

        public Person() {
            this.name = "null";
            this.age = 0;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
