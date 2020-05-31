package com.manhpd.shared.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public enum DatabaseConfig {

    INSTANCE;

    private static final Logger logger = LogManager.getLogger(DatabaseConfig.class);

    public Properties loadDatabaseConfig() throws IOException {
        Properties prop = this.readPropertiesFile(Constant.DB_CONFIG_PATH);
        return prop;
    }

    public Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;

        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException ex) {
            logger.error("readPropertiesFile() method: ", ex);
        } catch(IOException ex) {
            logger.error("readPropertiesFile() method: ", ex);
        } catch (Exception ex) {
            logger.error("readPropertiesFile() method: ", ex);
        } finally {
            if (Objects.nonNull(fis)) {
                fis.close();
            }
        }

        return prop;
    }

}
