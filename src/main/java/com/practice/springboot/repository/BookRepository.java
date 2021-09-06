package com.practice.springboot.repository;

import com.practice.springboot.dao.BookDAO;
import com.practice.springboot.entity.Book;
import com.practice.springboot.entity.BookDetails;
import com.practice.springboot.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BookRepository implements BookDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        Session session = entityManager.unwrap(Session.class);

        Query<Book> query = session.createQuery("from Book", Book.class);

        return query.getResultList();
    }

    @Override
    public Book findById(Long id) {
        Session session = entityManager.unwrap(Session.class);

        return session.get(Book.class, id);
    }

    @Override
    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Book where id=" + id);

        query.executeUpdate();
    }

    @Override
    public void save(Book data) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(data);
    }

    @Override
    public List<Book> findAvailableBooks() {
        Session session = entityManager.unwrap(Session.class);

        Query<Book> query = session.createQuery("from Book where student=null", Book.class);

        return query.getResultList();
    }

}
