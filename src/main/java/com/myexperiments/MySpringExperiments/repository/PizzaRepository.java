package com.myexperiments.MySpringExperiments.repository;

import com.myexperiments.MySpringExperiments.domain.Pizza;

public interface PizzaRepository {

    Pizza save(Pizza order);

}
