package com.myexperiments.springmvc.data.impl;

import com.myexperiments.springmvc.data.SpitterRepository;
import com.myexperiments.springmvc.model.Spitter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    private List<Spitter> spitterList = new ArrayList<>();

    @Override
    public Spitter save(Spitter spitter) {
        // Manage Updates
        Optional<Spitter> oldSpitter = spitterList.stream()
                .filter(aSpitter -> aSpitter.equals(spitter))
                .findFirst();

        oldSpitter.ifPresent(theOldSpitter -> spitterList.remove(theOldSpitter));

        Long max = spitterList.stream()
                .mapToLong(Spitter::getId)
                .max()
                .orElse(0L);

        spitter.setId(max + 1L);
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
