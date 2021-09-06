package com.practice.springboot.repository;

import com.practice.springboot.dao.BookDetailsDAO;
import com.practice.springboot.dto.BookDetailsDTO;
import com.practice.springboot.entity.Book;
import com.practice.springboot.entity.BookDetails;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BookDetailsRepository implements BookDetailsDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<BookDetails> findAll() {
        Session session = entityManager.unwrap(Session.class);

        Query<BookDetails> query = session.createQuery("from BookDetails", BookDetails.class);

        return query.getResultList();
    }

    @Override
    public BookDetails findById(Long id) {
        Session session = entityManager.unwrap(Session.class);

        return session.get(BookDetails.class, id);
    }

    @Override
    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from BookDetails where id=" + id);

        query.executeUpdate();
    }

    @Override
    public void save(BookDetails data) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(data);
    }


    @Override
    public List<Book> findIssuedBooksByStudentId(Long studentId) {
        Session session = entityManager.unwrap(Session.class);

        Query<Book> query = session.createQuery("from Book where student_id=" + studentId, Book.class);

        return query.getResultList();
    }

    @Override
    public BookDetails findByBookId(Long id) {
        Session session = entityManager.unwrap(Session.class);

        Query<BookDetails> query = session.createQuery("from BookDetails where book_id=" + id, BookDetails.class);

        return query.getSingleResult();
    }

}
