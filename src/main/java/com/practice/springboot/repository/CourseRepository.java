package com.practice.springboot.repository;

import com.practice.springboot.dao.CourseDAO;
import com.practice.springboot.entity.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CourseRepository implements CourseDAO {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Course> findAll() {
        Session session = entityManager.unwrap(Session.class);

        Query<Course> query = session.createQuery("from Course", Course.class);

        return query.getResultList();
    }

    @Override
    public Course findById(Long id) {
        Session session = entityManager.unwrap(Session.class);

        return session.get(Course.class, id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void save(Course course) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(course);
    }
}
