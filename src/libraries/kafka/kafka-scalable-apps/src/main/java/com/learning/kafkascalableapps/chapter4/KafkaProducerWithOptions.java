package com.learning.kafkascalableapps.chapter4;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.record.CompressionType;

import java.util.Properties;
import java.util.Random;

public class KafkaProducerWithOptions {

    public static void main(String[] args) {

        //Setup Properties for Kafka Producer
        Properties kafkaProps = new Properties();

        //List of brokers to connect to
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092,localhost:9093,localhost:9094");

        //Serializer class used to convert Keys to Byte Arrays
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        //Serializer class used to convert Messages to Byte Arrays
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        //Set ACKS to all so all replicas needs to acknolwedge
        kafkaProps.put(ProducerConfig.ACKS_CONFIG, "all");

        //Set compression type to GZIP
        kafkaProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,
                CompressionType.GZIP.name);

        //Create a Kafka producer from configuration
        KafkaProducer optionsProducer = new KafkaProducer(kafkaProps);

        //Use a Random number to generate message keys
        Random randomKey = new Random();

        /**************************************************************
         Publish Asynchronously without any checks
         **************************************************************/
        //Create the record
        ProducerRecord<String, String> asyncNoChecksRec =
                new ProducerRecord<String, String>(
                        "kafka.learning.orders",    //Topic name
                        String.valueOf(randomKey.nextInt(1000)),
                        "This is order published asynchronously with NO checks"
                );

        try {
            //No checks used
            optionsProducer.send(asyncNoChecksRec);

            System.out.println("\nSent Asynchronously, with no Checks :" + asyncNoChecksRec);

        } catch (Exception e) {
            System.out.println("Exception Publishing Asynchronously without Checks :" + e.getMessage());
        }

        /**************************************************************
         Publish Synchronously and check for results
         **************************************************************/
        //Create the record
        ProducerRecord<String, String> syncRec =
                new ProducerRecord<String, String>(
                        "kafka.learning.orders",    //Topic name
                        String.valueOf(randomKey.nextInt(1000)),
                        "This is order published synchronously"
                );

        //Send synchronously, wait for confirmation
        try {
            RecordMetadata retData =
                    (RecordMetadata) optionsProducer
                            .send(syncRec)
                            .get(); //Get makes it synchronous

            System.out.println("\nSent Synchronously :" + syncRec
                    + " Received Partition : " + retData.partition()
                    + " and Offset : " + retData.offset());

        } catch (Exception e) {
            System.out.println("Exception Publishing Synchronously:" + e.getMessage());
        }

        /**************************************************************
         Publish Asynchronously with a callback
         **************************************************************/

        String messageKey = String.valueOf(randomKey.nextInt(1000));
        //Create Message
        ProducerRecord<String, String> asyncRecCallBack =
                new ProducerRecord<String, String>(
                        "kafka.learning.orders",    //Topic name
                        messageKey,
                        "This is order published asynchronously with Callback"
                );

        //Send with Callback. Callback handler also has message key for context
        optionsProducer.send(asyncRecCallBack, new KafkaCallBack(messageKey));

        System.out.println("\nSent Asynchronously with Callback :" + asyncRecCallBack);

        optionsProducer.close();
    }
}


