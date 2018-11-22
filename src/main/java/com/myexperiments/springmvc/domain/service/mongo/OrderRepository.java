package com.myexperiments.springmvc.domain.service.mongo;

import com.myexperiments.springmvc.domain.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * When Spring Data MongoDB generates the repository implementation, it will generate a class called OrderRepositoryImpl
 * and the OrderOperationsImpl and mix it into the automatically generated implementation.
 */
public interface OrderRepository extends MongoRepository<Order, String>, OrderOperations {
	
	List<Order> findByCustomer(String customer);
	List<Order> findByCustomerLike(String customer);
	List<Order> findByCustomerAndType(String customer, String type);
	List<Order> getByType(String type);

	// It takes a JSON query string instead of a JPA query.
	@Query("{customer:'Chuck Wagon'}")
	List<Order> findChucksOrders();

}
