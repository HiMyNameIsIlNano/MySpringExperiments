package com.myexperiments.MySpringExperiments.repository;

import com.myexperiments.MySpringExperiments.domain.Order;

public interface OrderRepository {

    Order save(Order order);

}
