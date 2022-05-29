package com.manhpd.hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConcurrentModificationExceptionHashMap {
    public static void main(String[] args) {
        Map<String, Long> phoneBook = new HashMap<String, Long>(100, 0.9f);

        phoneBook.put("Vikram", 8149535110L);
        phoneBook.put("Mark", 7080535110L);
        phoneBook.put("Jim", 8923535110L);

        Iterator<String> keyIterator = phoneBook.keySet().iterator();

        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            if ("Vikram".equals(key)) {
                keyIterator.remove();
//                phoneBook.remove("Jim");
            }
        }
    }
}
