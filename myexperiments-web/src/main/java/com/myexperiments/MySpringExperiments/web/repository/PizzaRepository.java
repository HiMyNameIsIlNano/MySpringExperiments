package com.myexperiments.MySpringExperiments.web.repository;

import com.myexperiments.MySpringExperiments.web.domain.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {

}
