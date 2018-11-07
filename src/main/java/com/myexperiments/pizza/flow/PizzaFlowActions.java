package com.myexperiments.pizza.flow;

import com.myexperiments.pizza.domain.*;
import com.myexperiments.pizza.service.CustomerNotFoundException;
import com.myexperiments.pizza.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static com.myexperiments.pizza.domain.PaymentType.CREDIT_CARD;

@Component
public class PizzaFlowActions {

    @Autowired
    private CustomerService customerService;

    private static final Logger LOGGER = Logger.getLogger(PizzaFlowActions.class.getName());

    public Customer lookupCustomer(String phoneNumber) throws CustomerNotFoundException {
        try {
            return customerService.lookupCustomer(phoneNumber);
        } catch (CustomerNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }

    public void addCustomer(Customer customer) {
        LOGGER.warning("TODO: Flesh out the addCustomer() method.");
    }

    public Payment verifyPayment(PaymentDetails paymentDetails) {
        Payment payment = null;
        if(paymentDetails.getPaymentType() == CREDIT_CARD) {
            payment = new CreditCardPayment();
        } else {
            payment = new CashOrCheckPayment();
        }

        return payment;
    }

    public void saveOrder(Order order) {
        LOGGER.warning("TODO: Flesh out the saveOrder() method.");
    }

    public boolean checkDeliveryArea(String zipCode) {
        LOGGER.warning("TODO: Flesh out the checkDeliveryArea() method.");
        return "75075".equals(zipCode);
    }

}
