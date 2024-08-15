package com.neueda.spring_jpa_demo.data;

import com.neueda.spring_jpa_demo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public List<Customer> findAllByCountry(String country);
}
