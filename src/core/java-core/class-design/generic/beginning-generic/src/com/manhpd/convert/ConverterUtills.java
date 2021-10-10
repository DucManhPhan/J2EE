package com.manhpd.convert;

import java.util.ArrayList;
import java.util.List;

public class ConverterUtills {

    /**
     * The static method toList() accepts an array of type T[] and returns a list of type List<T>.
     * This is indicated by writing <T> at the beginning of the method signature, which declares T as a new type variable.
     * The scope of type variable T is local to the method itself, it may appear in the method signature and the method body,
     * but not outside the method.
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(T[] arr) {
        List<T> list = new ArrayList<T>();
        for (T elt : arr) {
            list.add(elt);
        }

        return list;
    }

    /**
     * The vararg feature permits a special,
     * more convenient syntax for the case in which the last argument of a method is an array
     *
     * At runtime, the arguments are packed into an array which is passed to the method.
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T> List<T> toLists(T... arr) {
        List<T> list = new ArrayList<T>();
        for (T elt : arr) {
            list.add(elt);
        }

        return list;
    }

}
