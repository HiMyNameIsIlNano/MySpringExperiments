package com.myexperiments.springmvc.domain.model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@NodeEntity
public class Order {

	/**
     * It is required that the @Id -annotated property be a Long.
     */
	@Id
	private Long id;
	
	private String customer;
	
	private String type;

	/**
     *  The type attribute essentially labels the relationship. It can be given any value, but it is commonly given
     *  human-readable text that briefly describes the nature of the relationship.
    */
	@Relationship(type="HAS_ITEMS")
	private Set<Item> items = new LinkedHashSet<Item>();

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Long getId() {
		return id;
	}
	
}
