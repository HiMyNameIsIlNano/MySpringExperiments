package com.myexperiments.springmvc.data.impl;

import com.myexperiments.springmvc.data.SpitterRepository;
import com.myexperiments.springmvc.model.Spitter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    private List<Spitter> spitterList = new ArrayList<>();

    @Override
    public Spitter save(Spitter spitter) {
        spitterList.remove(spitter);
        spitterList.add(spitter);
        return spitter;
    }

    @Override
    public Spitter findByUserName(String user) {
        return spitterList.stream()
                .filter(spitter -> user.equals(spitter.getUsername()))
                .findFirst()
                .orElse(null);
    }
}
