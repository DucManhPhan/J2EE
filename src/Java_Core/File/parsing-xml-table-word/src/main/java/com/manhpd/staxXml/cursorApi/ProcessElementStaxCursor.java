package com.manhpd.staxXml.cursorApi;

import com.manhpd.dto.LstParameter;
import com.manhpd.staxXml.IProcessElement;

import javax.xml.stream.XMLStreamReader;

public abstract class ProcessElementStaxCursor implements IProcessElement {

    protected XMLStreamReader streamReader;

    protected LstParameter lstParameter;

    public ProcessElementStaxCursor(XMLStreamReader streamReader, LstParameter lstParameter) {
        this.streamReader = streamReader;
        this.lstParameter = lstParameter;
    }

}
