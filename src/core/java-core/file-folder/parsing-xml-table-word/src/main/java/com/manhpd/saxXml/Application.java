package com.manhpd.saxXml;

import com.manhpd.dto.LstParameter;
import com.manhpd.poiWord.PoiWordConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Application {

    public static void main(String[] args) {
        String xmlFilePath = "./files/LST_PARAMETER.xml";
        String worldFilePath = "./files/LST_PARAMETER_TABLE.docx";
        BlockingQueue<LstParameter> data = new LinkedBlockingQueue<>();

        SaxXmlReader saxXmlReader = new SaxXmlReader(data, xmlFilePath);
        saxXmlReader.read();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new PoiWordConsumer(data, worldFilePath));

        executor.shutdown();
    }

}
