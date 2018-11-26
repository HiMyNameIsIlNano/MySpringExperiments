package com.myexperiments.springmvc.domain.service.neo4j;

import com.myexperiments.springmvc.domain.model.Order;

import java.util.List;

public class OrderOperationsImpl implements OrderOperations {

    @Override
    public List<Order> findSiAOrders() {
        return null;
    }

    /*
    TODO: to be fixed
    private final Neo4jOperations neo4j;

    @Autowired
    public OrderRepositoryImpl(Neo4jOperations neo4j) {
        this.neo4j = neo4j;
    }

    public List<Order> findSiAOrders() {
        Result<Map<String, Object>> result = neo4j.query(
                "match (o:Order)-[:HAS_ITEMS]->(i:Item) " +
                        "where i.product='Spring in Action' return o",
                EndResult<Order> endResult = result.to(Order.class);

                return IteratorUtil.asList(endResult);
    }*/

}
