package com.manhpd.saxXml;

import com.manhpd.IInitializationStage;
import com.manhpd.IReadXmlFile;
import com.manhpd.dto.LstParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class SaxXmlReader implements IReadXmlFile, IInitializationStage {

    private static final Logger logger = LogManager.getLogger(SaxXmlReader.class);

    private BlockingQueue<LstParameter> data;

    private String xmlFilePath;

    private SAXParser saxParser;

    public SaxXmlReader(BlockingQueue<LstParameter> data, String xmlFilePath) {
        Objects.requireNonNull(xmlFilePath);
        Objects.requireNonNull(data);

        this.xmlFilePath = xmlFilePath;
        this.data = data;
    }

    @Override
    public void read() {
        try {
            this.preInitialize();
            File xmlDocument = new File(this.xmlFilePath);
            this.saxParser.parse(xmlDocument, new SaxXmlHandler(this.data));
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public void preInitialize() {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            this.saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException ex) {
            logger.error(ex);
            throw new RuntimeException("Do not create an object of the SAXParser");
        }
    }

    @Override
    public void postInitialize() {
        throw new UnsupportedOperationException();
    }
}
