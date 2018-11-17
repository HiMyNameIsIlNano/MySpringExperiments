package com.myexperiments.springmvc.domain.service.impl;

import com.myexperiments.springmvc.domain.controller.Utils;
import com.myexperiments.springmvc.domain.service.SpittleRepository;
import com.myexperiments.springmvc.domain.model.Spittle;
import com.myexperiments.springmvc.security.condition.InMemoryCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Conditional(InMemoryCondition.class)
public class InMemorySpittleRepositoryImpl implements SpittleRepository {

    private List<Spittle> spittles = new ArrayList<>();

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        for (long i=1; i <= count; i++) {
            double latitude = Utils.getDoubleWithinRange(1, 40);
            double longitude = Utils.getDoubleWithinRange(1, 40);
            spittles.add(new Spittle(i, "Spittle " + i, new Date(), latitude, longitude));
        }

        return spittles;
    }

    @Override
    public Spittle findOne(long id) {
        return spittles.stream()
                .filter(spittle -> spittle.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Spittle addSpittle(Spittle spittle) {
        spittles.add(spittle);
        return spittle;
    }

}
