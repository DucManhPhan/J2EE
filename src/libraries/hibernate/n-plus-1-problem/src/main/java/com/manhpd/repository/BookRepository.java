package com.manhpd.repository;

import com.manhpd.entity.Book;
import com.manhpd.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class BookRepository implements IBookRepository {

    private static final String BOOK_ID = "id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long saveBookEntity(Book book) {
        return (long) this.entityManager.unwrap(Session.class)
                .save(book);
    }

    @Override
    public List<Book> findAll() {
        // 1st way
        final Session session = this.entityManager.unwrap(Session.class);
        return HibernateUtils.loadAllData(Book.class, session);

        // 2nd way
//        this.findAllWithJpql();
    }

    @Override
    public Book findById(long bookId) {
        final Session session = this.entityManager.unwrap(Session.class);
        final Criteria criteria = session.createCriteria(Book.class);
        criteria.add(Restrictions.eq(BOOK_ID, bookId));

        return (Book) criteria.uniqueResult();
    }

    private List<Book> findAllWithJpql() {
        final Session session = this.entityManager.unwrap(Session.class);
        return session.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }
}
