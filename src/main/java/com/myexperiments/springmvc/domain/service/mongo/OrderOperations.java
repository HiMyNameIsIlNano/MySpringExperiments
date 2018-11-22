package com.myexperiments.springmvc.domain.service.mongo;

import com.myexperiments.springmvc.domain.model.Order;

import java.util.List;

public interface OrderOperations {

    List<Order> findOrdersByType(String t);

}
