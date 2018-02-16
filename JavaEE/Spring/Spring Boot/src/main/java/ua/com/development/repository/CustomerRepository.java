package ua.com.development.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.development.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
