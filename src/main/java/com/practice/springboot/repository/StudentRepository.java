package com.practice.springboot.repository;

import com.practice.springboot.dao.StudentDAO;
import com.practice.springboot.dto.StudentDTO;
import com.practice.springboot.entity.Course;
import com.practice.springboot.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StudentRepository implements StudentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Student> findAll() {

        //get hibernate session
        Session session = entityManager.unwrap(Session.class);

        //create query
        Query<Student> query = session.createQuery("from Student", Student.class);

        //execute query and return list
        return query.getResultList();
    }

    @Override
    public Student findById(Long id) {
        Session session = entityManager.unwrap(Session.class);

        Student student = session.get(Student.class, id);

        return student;
    }

    @Override
    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("delete from Student where studentId=" + id);

        query.executeUpdate();
    }

    @Override
    public void save(Student student) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(student);
    }

    @Override
    public Student findByGuardianId(Long guardianId) {
        Session session = entityManager.unwrap(Session.class);

        Query<Student> query = session.createQuery("from Student where guardian_id=" + guardianId);

        return query.getSingleResult();
    }
}
