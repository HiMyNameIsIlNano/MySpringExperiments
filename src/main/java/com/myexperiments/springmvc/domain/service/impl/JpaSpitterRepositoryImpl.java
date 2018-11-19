package com.myexperiments.springmvc.domain.service.impl;

import com.myexperiments.springmvc.domain.model.Spitter;
import com.myexperiments.springmvc.domain.service.SpitterRepository;
import com.myexperiments.springmvc.security.condition.JpaCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
@Conditional(JpaCondition.class)
public class JpaSpitterRepositoryImpl implements SpitterRepository {


    /**
     * The JpaSpitterRepositoryImpl is given an EntityManager directly. There is no need for it to create one from
     * an EntityManagerFactory in each of its methods.
     * The @PersistenceContext does not inject an EntityManager and instead of giving the repository a real EntityManager,
     * it gives a proxy to a real EntityManager. That real EntityManager either is one associated with the current
     * transaction or, if one does not exist, it creates a new one. Thus, is one always working with an entity manager
     * in a thread-safe way.
     */
    @PersistenceContext
    private EntityManager em;

    public void addSpitter(Spitter spitter) {
        em.persist(spitter);
    }

    @Override
    public Spitter save(Spitter spitter) {
        em.merge(spitter);
        return spitter;
    }

    @Override
    public Spitter findByUserName(String user) {
        return em.find(Spitter.class, user);
    }

    @Override
    public Spitter findById(Long id) {
        return em.find(Spitter.class, id);
    }

}
