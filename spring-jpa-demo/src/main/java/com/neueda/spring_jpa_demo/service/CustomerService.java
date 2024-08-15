package com.neueda.spring_jpa_demo.service;

import com.neueda.spring_jpa_demo.data.CustomerRepository;
import com.neueda.spring_jpa_demo.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public List<Customer> getCustomersByCountry(String country){
        return customerRepository.findAllByCountry(country);
    }

    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Long count() {
        return customerRepository.count();
    }
}
