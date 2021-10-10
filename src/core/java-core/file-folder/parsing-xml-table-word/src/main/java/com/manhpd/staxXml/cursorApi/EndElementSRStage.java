package com.manhpd.staxXml.cursorApi;

import com.manhpd.dto.LstParameter;
import com.manhpd.utils.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLStreamReader;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class EndElementSRStage extends ProcessElementStaxCursor {

    private static final Logger logger = LogManager.getLogger(EndElementSRStage.class);

    private BlockingQueue<LstParameter> data;

    public EndElementSRStage(XMLStreamReader streamReader, LstParameter lstParameter,
                             BlockingQueue<LstParameter> data) {
        super(streamReader, lstParameter);
        this.data = data;
    }

    @Override
    public LstParameter process() {
        Optional.ofNullable(this.streamReader.getLocalName())
                .filter(Constant.LST_PARAMETER_NODE::equalsIgnoreCase)
                .ifPresent(name -> {
                    this.data.offer(this.lstParameter);
                    System.out.println("lstParameter: " + this.lstParameter.toString());
                });

        return this.lstParameter;
    }
}
