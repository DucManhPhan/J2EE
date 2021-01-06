package com.manhpd.staxXml.cursorApi;

import com.manhpd.dto.LstParameter;
import com.manhpd.staxXml.IProcessElement;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class ProcessElementCursorFactory {

    public static Optional<IProcessElement> create(XMLStreamReader streamReader, BlockingQueue<LstParameter> data,
                                                  LstParameter lstParameter) {
        Objects.requireNonNull(streamReader);
        Objects.requireNonNull(data);

        switch (streamReader.getEventType()) {
            case XMLStreamReader.START_ELEMENT:
                return Optional.of(new StartElementSRStage(streamReader, lstParameter));

            case XMLStreamConstants.END_ELEMENT:
                return Optional.of(new EndElementSRStage(streamReader, lstParameter, data));

            default:
                break;
        }

        return Optional.empty();
    }

}
