package com.myexperiments.springmvc.data;

import com.myexperiments.springmvc.model.Spitter;

public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUserName(String user);
}
