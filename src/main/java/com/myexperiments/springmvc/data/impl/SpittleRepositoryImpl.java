package com.myexperiments.springmvc.data.impl;

import com.myexperiments.springmvc.controller.Utils;
import com.myexperiments.springmvc.data.SpittleRepository;
import com.myexperiments.springmvc.model.Spittle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

    private List<Spittle> spittles = new ArrayList<>();

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        for (int i=0; i < count; i++) {
            double latitude = Utils.getDoubleWithinRange(1, 40);
            double longitude = Utils.getDoubleWithinRange(1, 40);
            spittles.add(new Spittle("Spittle " + i, new Date(), latitude, longitude));
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

}
