package com.myexperiments.springmvc.domain.service.neo4j;

import com.myexperiments.springmvc.domain.model.Order;

import java.util.List;

public interface OrderOperations {

    List<Order> findSiAOrders();

}