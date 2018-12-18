package com.myexperiments.MySpringExperiments.repository;

import com.myexperiments.MySpringExperiments.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
