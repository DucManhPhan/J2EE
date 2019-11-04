/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex9.process;

import ex9.util.KafkaUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 *
 * @author Dell
 */
public class ConsumerThread  {

    public static void main(String[] args) {
        

        //============================
        String connection = "http://178.128.222.84:9092"; //
        String group = "console-consumer-70275"; //
        String topic = "testTopic";//
        //============================
       // KafkaProducer kproc = kf.initProducer(connection, 16380);
        KafkaConsumer consumer = KafkaUtil.initConsumer(connection, group, topic);
        
        
        try {
          List topics = new ArrayList();
            topics.add(topic);
           // consumer.subscribe(topics);
            while (true) {
                 consumer.subscribe(Collections.singletonList(topic));
                 ConsumerRecords<String, String> consumerRecords = consumer.poll(10);

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
           consumer.close();
        }   
    }
}
