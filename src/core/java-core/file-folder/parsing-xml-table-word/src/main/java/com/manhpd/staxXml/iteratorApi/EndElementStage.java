package com.manhpd.staxXml.iteratorApi;

import com.manhpd.dto.LstParameter;
import com.manhpd.utils.Constant;

import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.XMLEvent;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class EndElementStage extends ProcessElementStaxIterator {

    private BlockingQueue<LstParameter> data;

    public EndElementStage(XMLEvent xmlEvent, BlockingQueue<LstParameter> data,
                           LstParameter lstParameter) {
        super(xmlEvent, lstParameter);
        this.data = data;
    }

    @Override
    public LstParameter process() {
        EndElement endElement = this.xmlEvent.asEndElement();
        Optional.ofNullable(endElement.getName().getLocalPart())
                .filter(Constant.LST_PARAMETER_NODE::equalsIgnoreCase)
                .ifPresent(name -> {
                    this.data.offer(this.lstParameter);
                    System.out.println("lstParameter: " + this.lstParameter.toString());
                });

        return this.lstParameter;
    }
}
