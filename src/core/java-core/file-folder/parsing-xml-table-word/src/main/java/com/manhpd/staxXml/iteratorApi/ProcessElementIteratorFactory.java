package com.manhpd.staxXml.iteratorApi;

import com.manhpd.dto.LstParameter;
import com.manhpd.staxXml.IProcessElement;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.XMLEvent;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class ProcessElementIteratorFactory {

    public static Optional<IProcessElement> create(XMLEventReader eventReader, XMLEvent xmlEvent,
                                                   BlockingQueue<LstParameter> data, LstParameter lstParameter) {
        if (xmlEvent.isStartElement()) {
            return Optional.of(new StartElementStage(eventReader, xmlEvent, lstParameter));
        } else if (xmlEvent.isEndElement()) {
            return Optional.of(new EndElementStage(xmlEvent, data, lstParameter));
        }

        return Optional.empty();
    }

}
