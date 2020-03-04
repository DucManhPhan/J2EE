package com.manhpd.utils;

import com.manhpd.dto.KafkaConnection;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Properties;

public class PropertiesUtils {

    private static final Logger logger = Logger.getLogger(PropertiesUtils.class);

    private static Optional<KafkaConnection> connection = Optional.empty();

    public static Optional<KafkaConnection> getKafkaConnection(String fileName) throws URISyntaxException {
        String path = FileUtils.getAbsolutePathOf(fileName);
        if (connection.isEmpty()) {
            connection = PropertiesUtils.getKafkaInfo(path);
        }

        return connection;
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

}

