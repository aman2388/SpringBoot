package com.neueda.spring_container_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringContainerDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringContainerDemoApplication.class, args);

		InvoiceUtilitiesInterface invoiceUtilities = context.getBean(InvoiceUtilitiesInterface.class);

		Invoice invoice = invoiceUtilities.generateInvoice();
		invoiceUtilities.payInvoice(invoice.getId());


	}

}
