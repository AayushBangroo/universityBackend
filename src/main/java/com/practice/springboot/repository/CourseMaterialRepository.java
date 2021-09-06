package com.practice.springboot.repository;

import com.practice.springboot.dao.CourseMaterialDAO;
import com.practice.springboot.entity.CourseMaterial;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CourseMaterialRepository implements CourseMaterialDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<CourseMaterial> findAll() {
        Session session = entityManager.unwrap(Session.class);

        Query<CourseMaterial> query = session.createQuery("from CourseMaterial", CourseMaterial.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public CourseMaterial findById(Long id) {
        Session session = entityManager.unwrap(Session.class);

        return session.get(CourseMaterial.class, id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void save(CourseMaterial courseMaterial) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(courseMaterial);
    }
}
