package com.practice.springboot.repository;

import com.practice.springboot.dao.GuardianDAO;
import com.practice.springboot.entity.Guardian;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class GuardianRespository implements GuardianDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Guardian findById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Guardian guardian = session.get(Guardian.class, id);

        return guardian;
    }

    @Override
    public void deleteById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from Guardian where guardianId=" + id);

        query.executeUpdate();
    }
}
