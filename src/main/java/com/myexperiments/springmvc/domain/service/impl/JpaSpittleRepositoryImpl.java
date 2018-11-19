package com.myexperiments.springmvc.domain.service.impl;

import com.myexperiments.springmvc.domain.model.Spittle;
import com.myexperiments.springmvc.domain.service.SpittleRepository;
import com.myexperiments.springmvc.security.condition.JpaCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@Transactional
@Conditional(JpaCondition.class)
public class JpaSpittleRepositoryImpl implements SpittleRepository {

    /**
     * The only problem with JpaSpittleRepositoryImpl is that each method ends up calling  createEntityManager().
     * This means means a new EntityManager is created every time one of the repository methods is called.
     * This complicates matters concerning transactions. The problem is that an EntityManager isn’t thread-safe and
     * generally should not be injected into a shared singleton bean like a repository.
     */
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public Spittle addSpittle(Spittle spittle) {
        emf.createEntityManager().persist(spittle);
        return spittle;
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return null;
    }

    @Override
    public Spittle findOne(long id) {
        return emf.createEntityManager().find(Spittle.class, id);
    }

}
