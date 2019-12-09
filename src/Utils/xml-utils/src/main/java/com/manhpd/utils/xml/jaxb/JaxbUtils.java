package com.manhpd.utils.xml.jaxb;

import com.manhpd.dto.book_store.BookStore;
import com.manhpd.dto.purchase_order.PurchaseOrder;
import com.manhpd.service.book_store_service.BookStoreService;
import com.manhpd.utils.Constants;
import com.manhpd.utils.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


/**
 * Refer: https://www.baeldung.com/jaxb
 *
 */
public class JaxbUtils {

    private static BookStoreService bookStoreService = new BookStoreService();

    public static void writeBookXmlFile(String path) throws JAXBException {
        if (!StringUtils.isValid(path)) {
            path = Constants.BOOKSTORE_XML;
        }

        var bookstore = bookStoreService.getBookstore();

        // create JAXB context and instantiate marshaller
        var context = JAXBContext.newInstance(BookStore.class);
        var m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
        m.marshal(bookstore, System.out);

        // Write to File
        m.marshal(bookstore, new File(path));
    }

    public static void readBookXmlFile(String path) throws FileNotFoundException, JAXBException {
        if (!StringUtils.isValid(path)) {
            path = Constants.BOOKSTORE_XML;
        }

        // create JAXB context and unmarshaller
        var context = JAXBContext.newInstance(BookStore.class);
        var um = context.createUnmarshaller();
        var bookstore = (BookStore) um.unmarshal(new InputStreamReader(
                new FileInputStream(path), StandardCharsets.UTF_8));
        var bookList = bookstore.getBooksList();

        bookList.forEach((book) -> {
            System.out.println(book);
        });
    }

    public static void readPurchaseOrderXmlFile(String path) throws JAXBException, FileNotFoundException {
        if (!StringUtils.isValid(path)) {
            path = Constants.PURCHASE_ORDER_XML;
        }

        // create JAXB context and unmarshaller
        var context = JAXBContext.newInstance(PurchaseOrder.class);
        var um = context.createUnmarshaller();
        var purchaseOrder = (PurchaseOrder) um.unmarshal(new InputStreamReader(
                new FileInputStream(path), StandardCharsets.UTF_8));

        // show result
        purchaseOrder.toString();
    }

}
