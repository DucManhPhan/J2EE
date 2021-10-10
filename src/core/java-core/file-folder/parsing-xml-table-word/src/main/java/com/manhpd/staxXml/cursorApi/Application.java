package com.manhpd.staxXml.cursorApi;

import com.manhpd.dto.LstParameter;
import com.manhpd.poiWord.PoiWordConsumer;
import com.manhpd.staxXml.iteratorApi.StaxIteratorXmlReader;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Application {

    public static void main(String[] args) {
        String xmlFilePath = "./files/LST_PARAMETER.xml";
        String worldFilePath = "./files/LST_PARAMETER_TABLE.docx";
        BlockingQueue<LstParameter> data = new LinkedBlockingQueue<>();

        StaxCursorXmlReader xmlReader = new StaxCursorXmlReader(data, xmlFilePath);
        xmlReader.read();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new PoiWordConsumer(data, worldFilePath));

        executor.shutdown();
    }

}
