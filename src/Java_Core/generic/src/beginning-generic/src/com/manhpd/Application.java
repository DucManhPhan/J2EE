package com.manhpd;

import com.manhpd.convert.ConverterUtills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * It's clearer and cleaner to use "equals()" method rather than == to compare values of reference type,
 * such as Integer and String.
 *
 */
public class Application {

    public static void main(String[] args) {

    }

    /**
     * In fact, the bytecode complied from the below two sources will be identical.
     * "erasure" is used to describe the process that converts the first program (1) to the second (2).
     *
     * Generics implicitly perform the same cast that is explicitly performed without generic.
     *
     * Implementing generics by erasure has a number of important effects. It keeps things
     * simple, in that generics do not add anything fundamentally new. It keeps things small,
     * in that there is exactly one implementation of List, not one version for each type. And
     * it eases evolution, since the same library can be accessed in both non-generic and generic
     * forms.
     *
     * Another consequence of implementing generics by erasure is that array types differ in
     * key ways from parameterized types. Executing
     *      new String[size]
     * allocates an array, and stores in that array an indication that its components are of type
     * String.
     */
    private static void originalGeneric() {
        // If use generic (1)
//        List<String> words = new ArrayList<>();
//        words.add("Hello");
//        words.add("world!");
//
//        String s = words.get(0) + words.get(1);
//        assert(s.equals("Hello Worlds=!"));

        // after easure, we will have the code (2)
        // Without generics, the type parameters are omitted, but we must explicit cast
        // whenever an element is extracted from the list.
        List words = new ArrayList();
        words.add("Hello ");
        words.add("world!");

        String s = ((String) words.get(0) + (String)words.get(1));
        assert s.equals("Hello world!");
    }

    public static int sum(List<Integer> ints) {
        int s = 0;
        for (int n : ints) {
            s += n;
        }

        return s;
    }

    /**
     * sum() method will be compiled to sumInteger() method.
     * The below code compiles but performs a lot of needless work.
     *
     * @param ints
     * @return
     */
    public static Integer sumInteger(List<Integer> ints) {
        Integer s = 0;
        for (Integer n : ints) {
            s += n;
        }

        return s;
    }

    public static void compareInteger() {
        List<Integer> bigs = Arrays.asList(100, 200, 300);

        // unboxing causes values to be compared, so the results are equal.
        assert sumInteger(bigs) == sum(bigs);

        // there is no unboxing, and the two method calls return distinct Integer objects,
        // so the results are unequal even though both Integer objects represent the same value.
        assert sumInteger(bigs) != sumInteger(bigs);
    }

    public static void originalForeach() {
        // use foreach --> correspond to what was written by the user
        List<Integer> ints = Arrays.asList(1, 2, 3);
        int s = 0;

        for (int n : ints) {
            s += n;
        }

        assert s == 6;

        // foreach is compiled to the below version, and added in a systematic way by the compiler
        // In general, compiler invents a new name that is guaranteed not to clash with any name already in our code.
        // unboxing occurs when the expression it.next() of type Integer is assigned to the variable n of type int.
        // The foreach loop can be applied to any object that implements the interface Iterable<E>
        // (in package java.lang), which in turn refers to the interface Iterator<E> (in
        // package java.util)
        for (Iterator<Integer> it = ints.iterator(); it.hasNext(); ) {
            int n = it.next();
            s += n;
        }
    }

    /**
     * Explicit parameters are usually not required, but they are helpful in our below example.
     *
     * Rule:
     * In a call to a generic method, if there are one or more arguments that correspond to a type parameter
     * and they all have the same type when the type parameter may be inferred;
     * If there are no arguments that corresponds to the type parameter or the arguments belong to different subtypes
     * of the intended type then the type parameter must be given explicitly.
     */
    public static void explicitTypeParameterGenericMethod() {
        List<Integer> ints = ConverterUtills.<Integer>toLists();
        List<Object> objs = ConverterUtills.<Object>toLists(1, "two");
    }

}
