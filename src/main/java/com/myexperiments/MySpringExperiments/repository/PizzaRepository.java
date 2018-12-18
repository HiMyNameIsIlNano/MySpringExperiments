package com.myexperiments.MySpringExperiments.repository;

import com.myexperiments.MySpringExperiments.domain.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {

}
