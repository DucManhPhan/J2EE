package com.manhpd.repository;

import com.manhpd.entity.Author;

import java.util.List;

public interface IAuthorRepository {

    Author findById(long authorId);

    List<Author> findAll();

}
