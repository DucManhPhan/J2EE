package com.manhpd;

import java.util.TreeSet;

public class TreeSetImplementation {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();

        set.add(5);
        set.add(1);
        set.add(7);
        set.add(3);

        System.out.println(set);

        set.add(3);
        System.out.println(set);

        set.remove(7);
        System.out.println(set);
        System.out.println("Descending order: " + set.descendingSet());
    }

}
