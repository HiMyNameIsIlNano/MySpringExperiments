package com.myexperiments.MySpringExperiments.repository;

import com.myexperiments.MySpringExperiments.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
