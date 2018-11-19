package com.myexperiments.springmvc.domain.service.impl;

import com.myexperiments.springmvc.domain.model.Spitter;
import com.myexperiments.springmvc.domain.service.SpitterRepository;
import com.myexperiments.springmvc.security.condition.HibernateCondition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@Conditional(HibernateCondition.class)
public class HibernateSpitterRepositoryImpl implements SpitterRepository {

    private final SessionFactory sessionFactory;

    /**
     * The constructor is annotated with @Autowired so that when it is created, it will be given a SessionFactory object.
     * SessionFactory is an Hibernate specific interface.
     */
    @Autowired
    public HibernateSpitterRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public long count() {
        return findAll().size();
    }

    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);
        return new Spitter(
                (Long) id,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getEmail(),
                spitter.getFirstName(),
                spitter.getLastName());
    }

    @Override
    public Spitter findByUserName(String user) {
        return (Spitter) currentSession()
                .createCriteria(Spitter.class)
                .add(Restrictions.eq("username", user))
                .list().get(0);
    }

    @Override
    public Spitter findById(Long id) {
        return (Spitter) currentSession().get(Spitter.class, id);
    }

    public List<Spitter> findAll() {
        return (List<Spitter>) currentSession()
                .createCriteria(Spitter.class).list();
    }

}
