package com.manhpd;


import com.manhpd.utils.xml.jaxb.JaxbUtils;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class JaxbApp {

    public static void main( String[] args ) throws JAXBException, FileNotFoundException {
//        JaxbUtils.writeXmlFile("");

//        JaxbUtils.readBookXmlFile("");

        JaxbUtils.readPurchaseOrderXmlFile("");
    }

}
