package com.pizza.actions;

import com.pizza.exception.CustomerNotFoundException;
import com.pizza.model.customer.Customer;
import com.pizza.model.order.Order;
import com.pizza.model.payment.PaymentDetails;
import org.springframework.stereotype.Component;

/**
 * @since: 31.05.15
 * @author: Андрей
 */

@Component
public class PizzaFlowActions {

    public Customer lookupCustomer(String phoneNumber) {
        throw new CustomerNotFoundException();
    }

    public boolean checkDeliveryArea(String zipCode) {
        return false;
    }

    public void addCustomer (Customer customer) {
         //save customer
    }

    public void saveOrder(Order order) {
        // save order
    }

    public PaymentDetails verifyPayment(PaymentDetails paymentDetails) {
        return paymentDetails;
    }
}
