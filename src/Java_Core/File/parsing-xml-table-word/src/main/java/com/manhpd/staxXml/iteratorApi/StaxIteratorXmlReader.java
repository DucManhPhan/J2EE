package com.manhpd.staxXml.iteratorApi;

import com.manhpd.IInitializationStage;
import com.manhpd.IReadXmlFile;
import com.manhpd.dto.LstParameter;
import com.manhpd.staxXml.IProcessElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class StaxIteratorXmlReader implements IReadXmlFile, IInitializationStage {

    private static final Logger logger = LogManager.getLogger(StaxIteratorXmlReader.class);

    private BlockingQueue<LstParameter> data;

    private String xmlFilePath;

    private XMLEventReader eventReader;

    public StaxIteratorXmlReader(BlockingQueue<LstParameter> data, String xmlFilePath) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(xmlFilePath);

        this.data = data;
        this.xmlFilePath = xmlFilePath;
    }

    @Override
    public void preInitialize() {
        try {
            File xmlFile = new File(this.xmlFilePath);

            // Instance the handler to access the tags in the XML file
            XMLInputFactory factory = XMLInputFactory.newInstance();

            // Initializing the handler to access the tags in the XML file
            this.eventReader = factory.createXMLEventReader(new FileReader(xmlFile));
        } catch (XMLStreamException | FileNotFoundException e) {
            logger.error(e.toString());
            throw new RuntimeException("Do not initialize Event Reader of Stax");
        }
    }

    @Override
    public void postInitialize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void read() {
        try {
            this.preInitialize();
            LstParameter lstParameter = null;

            while (this.eventReader.hasNext()) {
                XMLEvent xmlEvent = this.eventReader.nextEvent();
                Optional<IProcessElement> optProcessElement = ProcessElementIteratorFactory.create(this.eventReader, xmlEvent,
                                                                                                   this.data, lstParameter);
                lstParameter = optProcessElement.map(IProcessElement::process)
                                                .orElse(lstParameter);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }
}
