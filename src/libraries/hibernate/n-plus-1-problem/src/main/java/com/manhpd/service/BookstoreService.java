package com.manhpd.service;

import com.manhpd.entity.Author;
import com.manhpd.entity.Book;
import com.manhpd.repository.AuthorRepository;
import com.manhpd.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public void fetchBooksAndAuthors() {
        List<Book> books = this.bookRepository.findAll();

        for (Book book : books) {
            Author author = book.getAuthor();
            System.out.println("Book: " + book.getTitle() + " Author: " + author.getName());
        }
    }

    @Transactional(readOnly = true)
    public void fetchAuthorsAndBooks() {
        List<Author> authors = this.authorRepository.findAll();

        for (Author author : authors) {
            List<Book> books = author.getBooks();
            System.out.println("Author: " + author.getName() + " Books: " + books);
        }
    }

    public void insertBookToAuthor(long authorId, BookData data) {
        Author author = this.authorRepository.findById(authorId);
        Book book = this.buildBookEntity(data, author);
        long bookId = this.bookRepository.saveBookEntity(book);
        book.setId(bookId);
    }

    private Book buildBookEntity(BookData data, Author author) {
        Book currentBook = new Book();
        currentBook.setIsbn(data.getIsbn());
        currentBook.setTitle(data.getTitle());
        currentBook.setAuthor(author);

        return currentBook;
    }

}
