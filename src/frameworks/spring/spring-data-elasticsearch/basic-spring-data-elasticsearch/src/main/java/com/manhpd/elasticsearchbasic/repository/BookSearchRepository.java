package com.manhpd.elasticsearchbasic.repository;

import com.manhpd.elasticsearchbasic.domain.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookSearchRepository extends ElasticsearchRepository<Book, String> {
    List<Book> findByAuthorsNameContaining(String name);
}
