package com.manhpd.staxXml.iteratorApi;

import com.manhpd.dto.LstParameter;
import com.manhpd.staxXml.IProcessElement;

import javax.xml.stream.events.XMLEvent;

public abstract class ProcessElementStaxIterator implements IProcessElement {

    protected XMLEvent xmlEvent;

    protected LstParameter lstParameter;

    public ProcessElementStaxIterator(XMLEvent xmlEvent, LstParameter lstParameter) {
        this.xmlEvent = xmlEvent;
        this.lstParameter = lstParameter;
    }

}
