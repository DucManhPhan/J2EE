package com.manhpd.bookmanagement.service;

import com.manhpd.bookmanagement.entity.Book;
import com.manhpd.bookmanagement.exception.NotFoundBookException;
import com.manhpd.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book getOne(Long id) {
        return this.bookRepository.findById(id)
                                  .orElseThrow(NotFoundBookException::new);
    }

}
