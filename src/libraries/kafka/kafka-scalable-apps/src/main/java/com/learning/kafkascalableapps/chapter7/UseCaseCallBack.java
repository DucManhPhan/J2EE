package com.learning.kafkascalableapps.chapter7;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class UseCaseCallBack implements Callback {

    String messageKey;

    //Set message key to identify message. Additional information
    //can also be set here to provide context
    public UseCaseCallBack(String messageKey) {
        this.messageKey=messageKey;
    }
    @Override
    public void onCompletion(RecordMetadata retData, Exception e) {

        //Check if exception occured
        if (e != null) {
            System.out.println("Exception Publishing Asynchronously without Callback :"
                    + e.getMessage());
        }
        else {
            System.out.println(" Callback received for Message Key " + messageKey
                    + " returned Partition : " + retData.partition()
                    + " and Offset : " + retData.offset());
        }
    }
}
