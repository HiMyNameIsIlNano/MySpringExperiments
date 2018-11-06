package com.myexperiments.pizza.service;

import com.myexperiments.pizza.domain.Customer;

public interface CustomerService {

    Customer lookupCustomer(String phoneNumber) throws CustomerNotFoundException;

}
