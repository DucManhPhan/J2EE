/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex9.process;

import ex9.util.KafkaUtil;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class MainProcess {

    public static void main(String[] args) {

        String connection = "http://10.58.71.187:9092";
        String group = "console-consumer-70275";
        String topic = "testTopic";
        
        KafkaProducer producer = KafkaUtil.initProducer(connection, 1);
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        int i = 0;

        try {
            while (true) {
                System.out.print("Enter message: ");
                String ms = sc.nextLine();
                Date d = new Date();
                producer.send(new ProducerRecord(topic, Integer.toString(i), d.toString() + " - test message - " + i++ + "." + ms));

                List topics = new ArrayList();
                topics.add(topic);

                KafkaConsumer consumer = KafkaUtil.initConsumer(connection, group, topic);
                 consumer.subscribe(Collections.singletonList(topic));
                  final ConsumerRecords<String, String> consumerRecords = consumer.poll(10);
                for (ConsumerRecord<String, String> record : consumerRecords) {
                    System.out.println(String.format("Topic - %s, Partition - %d, Value: %s", ((ConsumerRecord) record).topic(), ((ConsumerRecord) record).partition(), ((ConsumerRecord) record).value()));
                }
                consumer.commitAsync();
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            producer.close();
            //consumer.close();
        }
    }
}
