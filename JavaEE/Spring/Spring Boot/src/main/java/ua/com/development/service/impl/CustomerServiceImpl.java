package ua.com.development.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.development.entity.Customer;
import ua.com.development.repository.CustomerRepository;
import ua.com.development.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void addCustomer(String name) {
        customerRepository.save(new Customer(name));
    }
}
