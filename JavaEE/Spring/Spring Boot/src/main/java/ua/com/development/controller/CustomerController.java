package ua.com.development.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.development.entity.Customer;
import ua.com.development.service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("id") int id) {
        return customerService.getCustomerById(id);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public void addCustomer(@RequestBody String name){
        customerService.addCustomer(name);
    }
}
