package com.manhpd.service.book_store_service;

import com.manhpd.dto.book_store.BookStore;

public class BookStoreService {

    private BookService bookService = new BookService();

    public BookStore getBookstore() {
        var bookstore = new BookStore();
        bookstore.setName("Fraport Bookstore");
        bookstore.setLocation("Livres belles");
        bookstore.setBookList(this.bookService.getBooks());

        return bookstore;
    }

}
