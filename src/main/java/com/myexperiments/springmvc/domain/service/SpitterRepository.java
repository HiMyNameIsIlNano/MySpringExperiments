package com.myexperiments.springmvc.domain.service;

import com.myexperiments.springmvc.domain.model.Spitter;

public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUserName(String user);
}
