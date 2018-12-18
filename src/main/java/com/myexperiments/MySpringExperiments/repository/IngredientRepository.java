package com.myexperiments.MySpringExperiments.repository;

import com.myexperiments.MySpringExperiments.domain.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);

}
