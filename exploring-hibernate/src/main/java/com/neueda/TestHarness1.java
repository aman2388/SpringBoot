package com.neueda;

import jakarta.persistence.*;

import java.util.List;

public class TestHarness1 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("invoiceManagerPersistenceUnit");
        EntityManager em = factory.createEntityManager();

        TypedQuery<Customer> getAllCustomersQuery = em.createQuery("select c from Customer c", Customer.class);
        List<Customer> customers = getAllCustomersQuery.getResultList();

        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }

        Customer customer81 = em.find(Customer.class, 81L);
        System.out.println("Customer 81 is " + customer81);

        TypedQuery<Customer> getSpecificCustomerQuery =
                em.createQuery("select c from Customer c where c.phone like '+33%'", Customer.class);
        Customer foundCustomer = getSpecificCustomerQuery.getSingleResult();
        System.out.println("Customer " + foundCustomer );

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer newCustomer = new Customer(1L, "Fast Trains Ltd","10 Any Road","Any Town", null, "AB1 2CD", "UK", "someemail@notvalid.com", "+44 123456789");
        em.persist(newCustomer);
        tx.commit();

        System.out.println("The new customer was given an ID of " + newCustomer.getId());
        em.close();
        factory.close();



    }
}
