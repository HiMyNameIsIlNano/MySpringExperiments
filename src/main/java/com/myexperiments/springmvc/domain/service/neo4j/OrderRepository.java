package com.myexperiments.springmvc.domain.service.neo4j;

import com.myexperiments.springmvc.domain.model.Order;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface OrderRepository extends Neo4jRepository<Order, Long>, OrderOperations {
	
	List<Order> findByCustomer(String customer);
	
	List<Order> findByCustomerLike(String customer);

	List<Order> findByCustomerAndType(String customer, String type);

	List<Order> getByType(String type);

	/**
	 * Query to	find all Order nodes that are related to an Item whose product property is equal to
	 * 'Spring in Action'.
	 */
	@Query("match (o:Order)-[:HAS_ITEMS]->(i:Item) " +
			"where i.product='Spring in Action' return o")
	List<Order> findSiAOrders();

}
