package com.manhpd.domXml;

import com.manhpd.dto.LstParameter;
import com.manhpd.poiWord.PoiWordConsumer;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.*;

public class DomXmlReaderTest {

    @Test
    public void read() {
        String xmlFilePath = "./files/LST_PARAMETER_RTMTQ.xml";
        String worldFilePath = "./files/LST_PARAMETER_TABLE.docx";
        BlockingQueue<LstParameter> data = new LinkedBlockingQueue<>();

        DomXmlReader domXmlReader = new DomXmlReader(data, xmlFilePath);
        domXmlReader.read();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new PoiWordConsumer(data, worldFilePath));

        executor.shutdown();
    }
}