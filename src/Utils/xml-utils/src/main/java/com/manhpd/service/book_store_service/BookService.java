package com.manhpd.service.book_store_service;

import com.manhpd.dto.book_store.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookService {

    public List<Book> getBooks() {
        var bookList = new ArrayList<Book>();

        var theGameBook = new Book();
        theGameBook.setIsbn("978-0060554736");
        theGameBook.setName("The Game");
        theGameBook.setAuthor("Neil Strauss");
        theGameBook.setPublisher("Harpercollins");
        bookList.add(theGameBook);

        var charlotteBook = new Book();
        charlotteBook.setIsbn("978-3832180577");
        charlotteBook.setName("Feuchtgebiete");
        charlotteBook.setAuthor("Charlotte Roche");
        charlotteBook.setPublisher("Dumont Buchverlag");
        bookList.add(charlotteBook);

        return bookList;
    }

}
