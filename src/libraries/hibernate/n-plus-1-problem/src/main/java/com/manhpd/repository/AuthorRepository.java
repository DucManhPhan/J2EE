package com.manhpd.repository;

import com.manhpd.entity.Author;
import com.manhpd.utils.HibernateUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class AuthorRepository implements IAuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author findById(long authorId) {
        final Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createQuery("SELECT a FROM Author a WHERE a.id = :authorId");
        query.setParameter("authorId", authorId);

        return (Author) query.getSingleResult();
    }

    @Override
    public List<Author> findAll() {
        final Session session = this.entityManager.unwrap(Session.class);
        return HibernateUtils.loadAllData(Author.class, session);
    }
}
