package com.manhpd.elasticsearchbasic.service;

import com.manhpd.elasticsearchbasic.domain.Author;
import com.manhpd.elasticsearchbasic.domain.Book;
import com.manhpd.elasticsearchbasic.repository.BookSearchRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchPage;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookSearchRepository bookSearchRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public static final String BOOK_ID_1 = "1";

    public static final String BOOK_ID_2 = "2";

    public static final String BOOK_ID_3 = "3";

    private Book book1;

    private Book book2;

    private Book book3;

    @BeforeEach
    public void beforeEach() {
        // delete and recreate index
        IndexOperations indexOperations = elasticsearchOperations.indexOps(Book.class);
        indexOperations.delete();
        indexOperations.create();
        indexOperations.putMapping(indexOperations.createMapping());

        // add 2 books to Elasticsearch
        Author markTwain = new Author().setId("1")
                .setName("Mark Twain");
        Author kaufman = new Author().setId("2")
                .setName("Amie Kaufman");
        Author spooner = new Author().setId("3")
                .setName("Meagan Spooner");

        book1 = this.bookSearchRepository.save(new Book().setId(BOOK_ID_1)
                .setName("The Mysterious Stranger")
                .setAuthors(Collections.singletonList(markTwain))
                .setSummary("This is a fiction book"));

        book2 = this.bookSearchRepository.save(new Book().setId(BOOK_ID_2)
                .setName("The Innocents Abroad")
                .setAuthors(Collections.singletonList(markTwain))
                .setSummary("This is a special book"));

        book3 = this.bookSearchRepository.save(new Book().setId(BOOK_ID_3)
                .setName("The other side of the Sky")
                .setAuthors(Arrays.asList(kaufman, spooner)));
    }

    @Test
    public void searchBook() {
        // define page request: return the first 10 results: sort by book's name ASC
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "name.raw");

        // case 1: search all books: should return 3 books
        Assertions.assertEquals(3,
                this.bookService.searchBooks(new BookService.BookSearchInput(), pageable).getTotalElements());

        // case 2: filter books by author Mark Twain: should return [book2, book1]
        SearchPage<Book> booksByAuthor = this.bookService.searchBooks(
                new BookService.BookSearchInput().setFilter(new BookService.BookFilter().setAuthorName("Mark Twain")),
                pageable); // sort by book name ASC
        Assertions.assertEquals(2, booksByAuthor.getTotalElements());

        Iterator<SearchHit<Book>> iterator = booksByAuthor.iterator();
        Assertions.assertEquals(book2, iterator.next().getContent()); // The Innocents Abroad
        Assertions.assertEquals(book1, iterator.next().getContent()); // The Mysterious Stranger

        // Case 3: search by text 'special': Should return book 2 because it has summary containing 'special'
        // one typo in the search text: (specila) is accepted thanks to `fuziness`
        SearchPage<Book> specialBook = bookService.searchBooks(
                new BookService.BookSearchInput().setSearchText("specila"),
                pageable); // book 2

        Assertions.assertEquals(1, specialBook.getTotalElements());
        Assertions.assertEquals(book2, specialBook.getContent().iterator().next().getContent()); // The Innocents Abroad
    }

}
