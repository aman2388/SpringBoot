package com.neueda.spring_jpa_demo;

import com.neueda.spring_jpa_demo.data.CustomerRepository;
import com.neueda.spring_jpa_demo.data.InvoiceRepository;
import com.neueda.spring_jpa_demo.entities.Customer;
import com.neueda.spring_jpa_demo.entities.Invoice;
import com.neueda.spring_jpa_demo.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class SpringJpaDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJpaDemoApplication.class, args);

		CustomerService customerService = context.getBean(CustomerService.class);
		customerService.getAllCustomers().stream().forEach(System.out::println);
		System.out.println(customerService.count());

		Customer customer81 = customerService.getCustomerById(81L).get();
		Invoice inv = new Invoice();
		inv.setCustomer(customer81);
		inv.setAmount(100.0);
		inv.setDate(LocalDate.now());
		customer81.getInvoices().add(inv);


		InvoiceRepository invoiceRepository = context.getBean(InvoiceRepository.class);
		invoiceRepository.save(inv);
		customerService.save(customer81);
		System.out.println(customer81);


	}


}
