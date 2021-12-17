package com.manhpd;

import com.manhpd.service.BookData;
import com.manhpd.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
//            System.out.println("\nFetch all books and authors ...");
//            this.bookstoreService.fetchBooksAndAuthors();
//
//            System.out.println("\nFetch all authors and books ...");
//            this.bookstoreService.fetchAuthorsAndBooks();

            BookData data = new BookData("001-JN", "Zero to One");
            this.bookstoreService.insertBookToAuthor(4, data);
        };
    }

}
