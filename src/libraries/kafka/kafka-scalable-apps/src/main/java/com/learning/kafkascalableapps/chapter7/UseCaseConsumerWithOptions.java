package com.learning.kafkascalableapps.chapter7;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class UseCaseConsumerWithOptions {

    public static void main(String[] args) {

        //Setup Properties for consumer
        Properties kafkaProps = new Properties();

        //List of Kafka brokers to connect to
        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092,localhost:9093,localhost:9094");

        //Deserializer class to convert Keys from Byte Array to String
        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        //Deserializer class to convert Messages from Byte Array to String
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        //Consumer Group ID for this consumer
        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG,
                "kafka-java-consumer");

        //Set to consume from the earliest message, on start when no offset is
        //available in Kafka
        kafkaProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest");

        /**********************************************************************
         *                  Set Batching Parameters
         **********************************************************************/

        //Set min bytes to 10 bytes
        kafkaProps.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 20);

        //Set max wait timeout to 100 ms
        kafkaProps.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 200);

        /**********************************************************************
         *                  Set Autocommit Parameters
         **********************************************************************/

        //Set auto commit to false
        kafkaProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        //Create a Consumer
        KafkaConsumer<String, String> usecaseConsumer =
                new KafkaConsumer<String, String>(kafkaProps);

        //Subscribe to the kafka.learning.orders topic
        usecaseConsumer.subscribe(Arrays.asList("kafka.usecase.students"));

        //Continuously poll for new messages
        while(true) {

            //Poll with timeout of 100 milli seconds
            ConsumerRecords<String, String> messages =
                    usecaseConsumer.poll(Duration.ofMillis(100));

            //Print batch of records consumed
            for (ConsumerRecord<String, String> message : messages) {
                System.out.println("Message fetched : " + message);
            }

            /**********************************************************************
             *                  Do Manual commit asynchronously
             **********************************************************************/
            usecaseConsumer.commitAsync();
        }


    }
}
