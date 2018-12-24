package com.myexperiments.MySpringExperiments.web.repository;

import com.myexperiments.MySpringExperiments.web.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
