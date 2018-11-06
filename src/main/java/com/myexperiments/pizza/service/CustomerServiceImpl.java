package com.myexperiments.pizza.service;

import com.myexperiments.pizza.domain.Customer;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer lookupCustomer(String phoneNumber) throws CustomerNotFoundException {

        if("9725551234".equals(phoneNumber)) {
            Customer customer = new Customer();
            customer.setId(1);
            customer.setName("John Doe");
            customer.setAddress("River Str.");
            customer.setCity("Springfield");
            customer.setState("TX");
            customer.setZipCode("12345");
            customer.setPhoneNumber(phoneNumber);
            return customer;
        } else {
            throw new CustomerNotFoundException();
        }

    }

}
