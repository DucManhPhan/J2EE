package com.manhpd.util;

import com.manhpd.dto.KafkaConnection;
import com.manhpd.dto.ThreadInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Properties;

public class PropertiesUtils {

    private static final Logger logger = LogManager.getLogger(PropertiesUtils.class);

    private static Optional<KafkaConnection> connection = Optional.empty();

    private static Optional<ThreadInfo> threadInfo = Optional.empty();

    public static Optional<KafkaConnection> getKafkaConnection(String fileName) throws URISyntaxException {
        String path = FileUtils.getAbsolutePathOf(fileName);
        if (connection.isEmpty()) {
            connection = PropertiesUtils.getKafkaInfo(path);
        }

        return connection;
    }

    public static Optional<ThreadInfo> getThreadInfo(String fileName) throws URISyntaxException {
        String path = FileUtils.getAbsolutePathOf(fileName);

        if (threadInfo.isEmpty()) {
            threadInfo = PropertiesUtils.getThreadInformation(path);
        }

        return threadInfo;
    }

    private static Optional<KafkaConnection> getKafkaInfo(String path) {
        try(InputStream inputKafka = new FileInputStream(path)) {
            Properties prop = new Properties();
            prop.load(inputKafka);

            // convert properties to dto KafkaConnection
            Optional<KafkaConnection> connection = Optional.ofNullable(ConverterUtils.toKafkaConnection(prop));
            return connection;
        } catch (FileNotFoundException e) {
            logger.info("Do not find the file's path - " + path);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private static Optional<ThreadInfo> getThreadInformation(String path) {
        try (InputStream input = new FileInputStream(path)) {
            Properties prop = new Properties();
            prop.load(input);

            return Optional.of(ConverterUtils.toThreadInfo(prop));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
