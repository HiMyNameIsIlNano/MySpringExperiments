package com.myexperiments.springmvc.domain.service.impl;

import com.myexperiments.springmvc.domain.model.Spittle;
import com.myexperiments.springmvc.domain.service.SpittleRepository;
import com.myexperiments.springmvc.security.condition.HibernateCondition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@Conditional(HibernateCondition.class)
public class HibernateSpittleRepositoryImpl implements SpittleRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateSpittleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Spittle addSpittle(Spittle spittle) {
        Serializable id = currentSession().save(spittle);
        return new Spittle(
                (Long )id,
                spittle.getMessage(),
                spittle.getTime(),
                spittle.getLatitude(),
                spittle.getLongitude()
        );
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return null;
    }

    @Override
    public Spittle findOne(long id) {
        return currentSession().get(Spittle.class, id);
    }

}
