package ua.com.development.service;

import ua.com.development.entity.Customer;

public interface CustomerService {

    Customer getCustomerById(int id);

    void addCustomer(String name);
}
