package com.manhpd.utils;

public class ValidationUtils {

    public static boolean hasClassExist(String name) {
        try {
            Class c = Class.forName(name);
            if (c != null)
                return true;
        } catch (ClassNotFoundException e) {
            System.out.println("" + e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        String clazzName = String.class.getName();
        System.out.println(clazzName);
        boolean existedClazz = hasClassExist(clazzName);

        System.out.println(existedClazz);
    }

}
