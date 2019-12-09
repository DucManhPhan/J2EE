package com.manhpd.dto.book_store;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(namespace = "com.manhpd")
public class BookStore {

    @XmlElementWrapper(name = "bookList")   // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElement(name = "book")  // XmlElement sets the name of the entities
    private List<Book> bookList;

    private String name;

    private String location;

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBooksList() {
        return bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}