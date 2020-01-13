package com.manhpd;


import com.manhpd.utils.KafkaUtil;
import com.manhpd.value_object.KafkaConfig;

public class App {
    public static void main(String[] args) {
        KafkaConfig kafkaConfig = KafkaUtil.getKafkaConfig();
        System.out.println(kafkaConfig.toString());
    }
}
