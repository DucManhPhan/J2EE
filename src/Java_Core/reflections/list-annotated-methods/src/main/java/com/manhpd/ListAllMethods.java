package com.manhpd;

import com.manhpd.dto.StudentDto;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * This class will list all methods in a class
 */
public class ListAllMethods {

    public static void main(String[] args) {
//        listMethods(StudentDto.class);

        listMethodsWithoutParentClass(StudentDto.class);

//        listReturnedTypesFromMethods(StudentDto.class);
    }

    public static Method[] listMethods(Class clazz) {
        final Method[] methods = clazz.getMethods();
        displayNameMethods(methods);

        return methods;
    }

    public static Method[] listMethodsWithoutParentClass(Class clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        displayNameMethods(declaredMethods);

        return declaredMethods;
    }

    public static void listReturnedTypesFromMethods(Class studentClazz) {
        Method[] methods = listMethods(studentClazz);
        Stream.of(methods).forEach(method -> {
            System.out.println(method.getName() + " has returned type: " + method.getReturnType() + " and parameters: " + method.getParameters().length);
        });
    }

    /**
     * display all methods's name
     * @param methods
     */
    private static void displayNameMethods(Method[] methods) {
        Stream.of(methods).forEach(method -> {
            System.out.println(method.getName());
        });
    }

    /**
     * Gets an array of all methods in a class hierarchy walking up to parent classes
     * @param objectClass the class
     * @return the methods array
     */
    public static Method[] getAllMethodsInHierarchy(Class<?> objectClass) {
        Set<Method> allMethods = new HashSet<Method>();
        Method[] declaredMethods = objectClass.getDeclaredMethods();
        Method[] methods = objectClass.getMethods();
        if (objectClass.getSuperclass() != null) {
            Class<?> superClass = objectClass.getSuperclass();
            Method[] superClassMethods = getAllMethodsInHierarchy(superClass);
            allMethods.addAll(Arrays.asList(superClassMethods));
        }

        allMethods.addAll(Arrays.asList(declaredMethods));
        allMethods.addAll(Arrays.asList(methods));
        return allMethods.toArray(new Method[allMethods.size()]);
    }

}
