package com.manhpd.domXml;

import com.manhpd.IInitializationStage;
import com.manhpd.IReadXmlFile;
import com.manhpd.dto.LstParameter;
import com.manhpd.utils.Constant;
import com.manhpd.utils.ConverterUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class DomXmlReader implements IReadXmlFile, IInitializationStage {

    private static final Logger logger = LogManager.getLogger(DomXmlReader.class);

    private BlockingQueue<LstParameter> data;

    private Document xmlDoc;

    private String xmlFilePath;

    public DomXmlReader(BlockingQueue<LstParameter> data, String xmlFilePath) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(xmlFilePath);

        this.data = data;
        this.xmlFilePath = xmlFilePath;
    }

    @Override
    public void preInitialize() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            this.xmlDoc = builder.parse(xmlFilePath);
            Element root = this.xmlDoc.getDocumentElement();
            root.normalize();

            logger.info("Root element: " + root.getNodeName());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public void postInitialize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void read() {
        this.preInitialize();

        NodeList nodes = this.xmlDoc.getElementsByTagName(Constant.LST_PARAMETER_NODE);
        IntStream.rangeClosed(0, nodes.getLength() - 1)
                .filter(idx -> this.isElementDomNode(nodes.item(idx)))
                .mapToObj(idx -> (Element) nodes.item(idx))
                .map(ConverterUtils::toLstParameter)
                .peek(System.out::println)
                .forEach(lstParameter -> this.data.add(lstParameter));
    }

    private boolean isElementDomNode(Node domNode) {
        return domNode.getNodeType() == Node.ELEMENT_NODE;
    }
}
