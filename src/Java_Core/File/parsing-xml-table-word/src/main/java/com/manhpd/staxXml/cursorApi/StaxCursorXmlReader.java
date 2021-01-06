package com.manhpd.staxXml.cursorApi;

import com.manhpd.IInitializationStage;
import com.manhpd.IReadXmlFile;
import com.manhpd.dto.LstParameter;
import com.manhpd.staxXml.IProcessElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class StaxCursorXmlReader implements IReadXmlFile, IInitializationStage {

    private static final Logger logger = LogManager.getLogger(StaxCursorXmlReader.class);

    private String xmlFilePath;

    private XMLStreamReader streamReader;

    private BlockingQueue<LstParameter> data;

    public StaxCursorXmlReader(BlockingQueue<LstParameter> data, String xmlFilePath) {
        this.data = data;
        this.xmlFilePath = xmlFilePath;
    }

    @Override
    public void preInitialize() {
        try {
            File xmlFile = new File(this.xmlFilePath);

            // Instance the handler to access the tags in the XML file
            XMLInputFactory factory = XMLInputFactory.newInstance();

            this.streamReader = factory.createXMLStreamReader(new FileReader(xmlFile));
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

            while (this.streamReader.hasNext()) {
                this.streamReader.next();
                Optional<IProcessElement> optProcessElement = ProcessElementCursorFactory.create(this.streamReader,
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
