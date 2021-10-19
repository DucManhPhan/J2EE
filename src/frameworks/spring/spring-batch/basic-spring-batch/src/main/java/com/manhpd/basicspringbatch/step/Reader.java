package com.manhpd.basicspringbatch.step;


import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {

    private String[] messages = {"test-spring-batch-framework",
                                 "Welcome to Spring Batch example",
                                 "We use H2 Database for this example"};

    private int count = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (this.count < this.messages.length) {
            return this.messages[count++];
        } else {
            this.count = 0;
        }

        return null;
    }

}
