package com.myexperiments.springmvc.domain.service.neo4j;

import com.myexperiments.springmvc.domain.model.Order;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface OrderRepository extends Neo4jRepository<Order, Long> {
	
	List<Order> findByCustomer(String customer);
	
	List<Order> findByCustomerLike(String customer);

	List<Order> findByCustomerAndType(String customer, String type);

	List<Order> getByType(String type);
		
//	@Query("{customer:'Chuck Wagon'}")
//	List<Order> findChucksOrders();

}
