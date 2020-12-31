package com.manhpd.domXml;

import com.manhpd.IReadXmlFile;
import com.manhpd.dto.LstParameter;

import java.util.concurrent.BlockingDeque;

public class DomXmlReader implements IReadXmlFile {

    private BlockingDeque<LstParameter> data;

    public DomXmlReader(BlockingDeque<LstParameter> data) {
        this.data = data;
    }

    @Override
    public void read(String xmlFilePath) {

    }
}
