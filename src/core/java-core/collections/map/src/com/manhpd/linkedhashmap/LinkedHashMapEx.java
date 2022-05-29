package com.manhpd.linkedhashmap;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Ref: https://medium.com/javarevisited/complete-guide-on-linkedhashmap-in-java-93192a7e2e92
 */
public class LinkedHashMapEx {
    public static void main(String[] args) {
        LinkedHashMap<String, String> cityPoints = new LinkedHashMap<>();

        cityPoints.put("Pune", "Ganesh Temple");
        cityPoints.put("Mumbai", "Gateway of India");
        cityPoints.put("Delhi", "Red fort");

        Iterator<String> cities = cityPoints.keySet().iterator();
        while (cities.hasNext()) {
            String city = cities.next();
            System.out.println("City name: " + city);
        }
    }

}
