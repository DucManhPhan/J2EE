package com.manhpd.util;

import com.manhpd.dto.KafkaConnection;
import com.manhpd.dto.ThreadInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public static ThreadInfo toThreadInfo(Properties prop) {
        int numOfThreads = Integer.valueOf(prop.getProperty("thread.number"));
        ThreadInfo info = new ThreadInfo(numOfThreads);

        return info;
    }

}
