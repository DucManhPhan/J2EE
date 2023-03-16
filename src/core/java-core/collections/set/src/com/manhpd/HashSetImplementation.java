package com.manhpd;

import java.util.HashSet;
import java.util.Set;

public class HashSetImplementation {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();

        set.add(5);
        set.add(1);
        set.add(7);
        set.add(3);

        System.out.println(set);

        set.add(3);
        System.out.println(set);

        set.remove(7);
        System.out.println("After removing an element from Set, " + set);
    }
}
