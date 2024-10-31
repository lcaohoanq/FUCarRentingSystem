package com.lcaohoanq.fucar.services;

import com.lcaohoanq.fucar.models.Customer;
import java.util.List;

public interface ICustomerService {
    void save(Customer customer);
    List<Customer> findAll();
    void delete(Integer id);
    Customer findById(Integer id);
}
