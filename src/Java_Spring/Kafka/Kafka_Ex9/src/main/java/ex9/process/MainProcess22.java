/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex9.process;

import ex9.util.KafkaUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Future;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 *
 * @author Dell
 */
public class MainProcess22 {

    public static void main(String[] args) {
        //============================
        String connection = "http://178.128.222.84:9092"; //
        String group = "console-consumer-70275"; //
        String topic = "testTopic";//
        //============================
        KafkaProducer producer = KafkaUtil.initProducer(connection, 1);
        KafkaConsumer consumer = KafkaUtil.initConsumer(connection, group, topic);

        long time = System.currentTimeMillis();

        Date d = new Date();
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Enter message: ");
                String ms = sc.nextLine();
                final ProducerRecord<String, String> record = new ProducerRecord<>("." + topic, "." + d.toString() + ": test message from Ha - " + ms);
              
                Future<RecordMetadata> test = producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        if (e != null) {
                            String val = record.value().toString();
                            System.out.println(val);
                        }
                    }
                });

                consumer.subscribe(Collections.singletonList(topic));
                final ConsumerRecords<String, String> consumerRecords = consumer.poll(10);

                for (ConsumerRecord conRecord : consumerRecords) {
                    System.out.printf("Consumer Record:(%s, %s, %d, %d)\n",
                            conRecord.key(), conRecord.value(),
                            conRecord.partition(), conRecord.offset());
                }
                consumer.commitAsync();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            producer.flush();
            producer.close();
            consumer.close();
        }
    }
}
