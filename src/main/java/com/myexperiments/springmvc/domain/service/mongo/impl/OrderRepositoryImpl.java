package com.myexperiments.springmvc.domain.service.mongo.impl;

import com.myexperiments.springmvc.domain.model.Order;
import com.myexperiments.springmvc.domain.service.mongo.OrderOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class OrderRepositoryImpl implements OrderOperations {

    // Interface to the MongoTemplate.
    @Autowired
    private MongoOperations mongo;

    @Override
    public List<Order> findOrdersByType(String t) {
        String type = t.equals("NET") ? "WEB" : t;
        Criteria where = Criteria.where("type").is(type);
        Query query = Query.query(where);
        return mongo.find(query, Order.class);
    }
}
