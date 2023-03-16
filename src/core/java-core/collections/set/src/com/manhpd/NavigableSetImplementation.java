package com.manhpd;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.TreeSet;

public class NavigableSetImplementation {
    public static void main(String[] args) {
        AbstractSet<String> a = new TreeSet<>();
        a.addAll(Arrays.asList(new String[]{"twitter", "google", "meta", "netflix", "meta"}));
        System.out.println("Set with a: " + a);

        AbstractSet<String> b = new TreeSet<>();
        b.addAll(Arrays.asList(new String[]{"twitter", "google", "meta", "netflix", "meta"}));
        System.out.println("Set with b: " + b);

        boolean isEqual = a.equals(b);
        System.out.println("Set a == Set b: " + isEqual);

        int hashCodeA = a.hashCode();
        int hashCodeB = b.hashCode();

        System.out.println("HashCode of a is: " + hashCodeA);
        System.out.println("HashCode of b is: " + hashCodeB);
    }
}
