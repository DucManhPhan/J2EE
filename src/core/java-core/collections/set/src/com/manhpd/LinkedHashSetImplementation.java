package com.manhpd;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetImplementation {

    public static void main(String[] args) {
        Set<Integer> set = new LinkedHashSet<>();

        set.add(5);
        set.add(1);
        set.add(7);
        set.add(9);

        System.out.println(set);

        set.add(7);
        System.out.println(set);

        set.remove(7);
        System.out.println(set);
    }

}
