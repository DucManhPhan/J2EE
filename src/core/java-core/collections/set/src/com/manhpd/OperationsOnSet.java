package com.manhpd;

import java.beans.beancontext.BeanContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OperationsOnSet {

    public static void main(String[] args) {
        Set<Integer> a = new HashSet<>();
        a.addAll(Arrays.asList(new Integer[]{1, 3, 2, 4, 8, 9, 0}));

        Set<Integer> b = new HashSet<>();
        b.addAll(Arrays.asList(new Integer[]{1, 3, 7, 5, 4, 0, 7, 5}));

        // union operation
        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);
        System.out.println("Union of the two Set: " + union);

        // intersection operation
        Set<Integer> intersection = new HashSet<>(a);
        intersection.retainAll(a);
        System.out.println("Intersection of two Set: " + intersection);

        // symmetric difference operation
        Set<Integer> difference = new HashSet<>(a);
        difference.removeAll(b);
        System.out.println("Difference of two Set: " + difference);
    }

}
