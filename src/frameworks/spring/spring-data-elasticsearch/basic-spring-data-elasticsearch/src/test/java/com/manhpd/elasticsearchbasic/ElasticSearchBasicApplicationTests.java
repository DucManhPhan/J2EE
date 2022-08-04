package com.manhpd.elasticsearchbasic;

import com.manhpd.elasticsearchbasic.domain.Author;
import com.manhpd.elasticsearchbasic.domain.Book;
import com.manhpd.elasticsearchbasic.repository.BookSearchRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class ElasticSearchBasicApplicationTests {

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
                                                         .setAuthors(Collections.singletonList(markTwain)));

        book2 = this.bookSearchRepository.save(new Book().setId(BOOK_ID_2)
                                                         .setName("The Innocents Abroad")
                                                         .setAuthors(Collections.singletonList(markTwain)));

        book3 = this.bookSearchRepository.save(new Book().setId(BOOK_ID_3)
                                                         .setName("The other side of the Sky")
                                                         .setAuthors(Arrays.asList(kaufman, spooner)));
    }

    @Test
    public void findById() {
        Assertions.assertEquals(book1, this.bookSearchRepository.findById(BOOK_ID_1).orElse(null));
        Assertions.assertEquals(book2, this.bookSearchRepository.findById(BOOK_ID_2).orElse(null));
        Assertions.assertEquals(book3, this.bookSearchRepository.findById(BOOK_ID_3).orElse(null));
    }

    @Test
    public void query() {
        List<Book> books = this.bookSearchRepository.findByAuthorsNameContaining("Mark");

        Assertions.assertEquals(2, books.size());
        Assertions.assertEquals(book1, books.get(0));
        Assertions.assertEquals(book2, books.get(1));
    }

}
