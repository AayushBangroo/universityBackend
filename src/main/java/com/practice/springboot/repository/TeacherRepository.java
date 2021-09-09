package com.practice.springboot.repository;

import com.practice.springboot.dao.TeacherDAO;
import com.practice.springboot.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TeacherRepository implements TeacherDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Teacher> findAll() {
        Session session = entityManager.unwrap(Session.class);

        Query<Teacher> query = session.createQuery("from Teacher", Teacher.class);

        return query.getResultList();
    }

    @Override
    public Teacher findById(Long id) {
        Session session = entityManager.unwrap(Session.class);

        return session.get(Teacher.class, id);
    }

    @Override
    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("delete from Teacher where teacherId=" + id);

        query.executeUpdate();
    }

    @Override
    public void save(Teacher teacher) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(teacher);
    }
}
