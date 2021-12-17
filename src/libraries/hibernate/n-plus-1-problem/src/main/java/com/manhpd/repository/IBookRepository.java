package com.manhpd.repository;

import com.manhpd.entity.Book;

import java.util.List;

public interface IBookRepository {

    long saveBookEntity(Book book);

    List<Book> findAll();

    Book findById(long bookId);

}
