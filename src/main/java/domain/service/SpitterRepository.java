package domain.service;

import domain.model.Spitter;

public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUserName(String user);
}
