package com.manhpd.staxXml.iteratorApi;

import com.manhpd.dto.LstParameter;
import com.manhpd.utils.Constant;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StartElementStage extends ProcessElementStaxIterator {

    private static final Logger logger = LogManager.getLogger(StartElementStage.class);

    private XMLEventReader eventReader;

    private String[] fields = new String[]{
        Constant.PARAM_ID_FIELD,
        Constant.SYSTEM_ID_FIELD,
        Constant.APP_CODE_FIELD,
        Constant.PARAM_NAME_FIELD,
        Constant.PARAM_VALUE_FIELD,
        Constant.PARAM_MEANING_FIELD
    };

    public StartElementStage(XMLEventReader eventReader, XMLEvent xmlEvent,
                             LstParameter lstParameter) {
        super(xmlEvent, lstParameter);
        this.eventReader = eventReader;
    }

    @Override
    public LstParameter process() {
        try {
            StartElement startElement = this.xmlEvent.asStartElement();
            String elementName = startElement.getName().getLocalPart();

            Optional.ofNullable(elementName)
                    .filter(Constant.LST_PARAMETER_NODE::equalsIgnoreCase)
                    .ifPresent(name -> this.lstParameter = new LstParameter());

            this.doAttributes(startElement);
            this.doEachField(elementName);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }

        return this.lstParameter;
    }

    private void doAttributes(StartElement startElement) {
        Iterator<Attribute> iterator = startElement.getAttributes();
        while (iterator.hasNext()) {
            Attribute attribute = iterator.next();
            QName name = attribute.getName();

            // work with sequentially element
            // ...
            System.out.println(name.getLocalPart());
        }
    }

    private void doEachField(String elementName) {
        Predicate<String> hasExistField = fieldName -> Stream.of(fields)
                                                             .anyMatch(name -> name.equalsIgnoreCase(fieldName));

        Optional.ofNullable(elementName)
                .filter(hasExistField)
                .ifPresent(name -> {
                    try {
                        Characters dataEvent = (Characters) this.eventReader.nextEvent();
                        BeanUtils.setProperty(this.lstParameter, name, dataEvent.getData());
                    } catch (Exception ex) {
                        logger.error(ex);
                    }
                });
    }
}
