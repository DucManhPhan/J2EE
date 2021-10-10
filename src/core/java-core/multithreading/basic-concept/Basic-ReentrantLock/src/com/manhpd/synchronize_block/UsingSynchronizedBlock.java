package com.manhpd.synchronize_block;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


/**
 * In this re-entrance case that using synchronized keyword with same lock object,
 * we can see that only one method will be able to run it at the same time.
 *
 * We can refer this link: https://stackoverflow.com/questions/15438727/if-i-synchronized-two-methods-on-the-same-class-can-they-run-simultaneously
 */
public class UsingSynchronizedBlock {

    public static void main(String[] args) {
        Object lockObject = new Object();
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Integer> lstThreads = new ArrayList<>() {{
            add(10000);
            add(100000);
        }};
//        IntStream.range(20, 22)
        lstThreads.stream()
         .forEach(item -> {
             service.execute(
                     new PersonThread(lockObject, "Person thread " + item, item)
             );
         });

        service.shutdown();
    }

}
