package com.manhpd.utils;

import com.manhpd.dto.KafkaConnection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConverterUtils {

    private static final Logger logger = LogManager.getLogger(ConverterUtils.class);

    public static KafkaConnection toKafkaConnection(Properties prop) {
        // get list of topics
        String topicProperty = prop.getProperty("topics");
        List<String> topics = Arrays.asList(topicProperty.split(","));

        KafkaConnection connection = KafkaConnection.builder()
                .connection(prop.getProperty("connection"))
                .group(prop.getProperty("group"))
                .topics(topics)
                .build();

        return connection;
    }

}
