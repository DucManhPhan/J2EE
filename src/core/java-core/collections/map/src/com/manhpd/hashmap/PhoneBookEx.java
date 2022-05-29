package com.manhpd.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Ref: https://medium.com/javarevisited/internal-working-of-hashmap-in-java-97aeac3c7beb
 */
public class PhoneBookEx {
    public static void main(String[] args) {
        Map<String, Long> myPhoneBook = new HashMap<String, Long>(100, 0.9f);

        myPhoneBook.put("Vikram", 8149535110L);
        myPhoneBook.put("Mark", 7080535110L);
        myPhoneBook.put("John", 8923535110L);

        Set<Map.Entry<String, Long>> myPhoneBookSet = myPhoneBook.entrySet();
        System.out.println("---------: Contacts in my Phone List :----------");
        for (Map.Entry<String, Long> phoneEntry : myPhoneBookSet) {
            System.out.println("Name : " + phoneEntry.getKey() + " " + " Number : " + phoneEntry.getValue());
        }

        System.out.println("------------------------------------------------");
        System.out.println("No of contacts in myPhoneBook : " + myPhoneBook.size());
        System.out.println("Vikram's Contact number : " + myPhoneBook.get("Vikram"));
        System.out.println("Delete Mark's contact : " + myPhoneBook.remove("Mark"));
    }

}
