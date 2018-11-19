package com.myexperiments.springmvc.domain.service.impl;

import com.myexperiments.springmvc.domain.exceptions.AppWideDuplicateSpitterException;
import com.myexperiments.springmvc.domain.exceptions.DuplicateSpitterException;
import com.myexperiments.springmvc.domain.model.Spitter;
import com.myexperiments.springmvc.domain.service.SpitterRepository;
import com.myexperiments.springmvc.security.condition.InMemoryCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Conditional(InMemoryCondition.class)
public class InMemorySpitterRepositoryImpl implements SpitterRepository {

    private List<Spitter> spitterList = new ArrayList<>();

    @Override
    public Spitter save(Spitter spitter) throws DuplicateSpitterException {
        // Check for duplicates
        Optional<Spitter> oldSpitter = spitterList.stream()
                .filter(aSpitter -> aSpitter.getUsername().equals(spitter.getUsername()))
                .findFirst();

        // If duplicate exists
        if (oldSpitter.isPresent()) {
            throw new AppWideDuplicateSpitterException();
        }

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

    @Override
    public Spitter findById(Long id) {
        return spitterList.stream()
                .filter(spitter -> Objects.equals(id, spitter.getId()))
                .findFirst()
                .orElse(null);
    }
}
