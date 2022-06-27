package com.manhpd.bookmanagement.service;

import com.manhpd.bookmanagement.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book getOne(Long id);

}
