/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex9.process;

import ex9.util.KafkaUtil;
import java.lang.invoke.MethodHandles;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author Dell
 */
public class ProducerThread {

    public static void main(String[] args) {

        KafkaUtil kf = new KafkaUtil();

        //============================
        String connection = "http://178.128.22.84:9092"; //
        // String group = "console-consumer-70275"; //
       String topic = "testTopic";//
        //============================
        KafkaProducer kproc = kf.initProducer(connection, 1);

        ProducerRecord pr = new ProducerRecord(topic, "test","from netbeans");
        kproc.send(pr);
        System.out.println("Done!!!!");
       
//        try {
//            for (int i = 0; i < 5; i++) {
//                System.out.println(i);
//                kproc.send(new ProducerRecord(topic, Integer.toString(i), "test message - " + i));
//            }
//        } catch (Exception e) {
//            System.out.println("error " + e.toString());
//
//        } finally {
//            kproc.close();
//        }
    }
}