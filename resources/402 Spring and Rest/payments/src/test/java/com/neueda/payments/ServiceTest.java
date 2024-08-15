package com.neueda.payments;

import com.neueda.payments.control.CountriesController;
import com.neueda.payments.exceptions.PaymentNotfoundException;
import com.neueda.payments.model.Payment;
import com.neueda.payments.repositories.PaymentsRepository;
import com.neueda.payments.repositories.UsersRepository;
import com.neueda.payments.service.PaymentsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ServiceTest {
    @Autowired
    PaymentsService paymentsService;

    @Autowired
    CountriesController countriesController;

    @MockBean
    private PaymentsRepository paymentsRepository;

    @MockBean
    private UsersRepository usersRepository;

    @BeforeEach
    public void setUp(){
        Payment p1 = new Payment();
        p1.setCountry("IRL");

        Payment p2 = new Payment();
        p1.setCountry("CAN");

        Payment p3 = new Payment();
        p1.setCountry("USA");

        Payment p4 = new Payment();
        p1.setCountry("IRL");

        Payment p5 = new Payment();
        p1.setCountry("FRA");

        Payment p6 = new Payment();
        p1.setCountry("CAN");

        Mockito.when(paymentsRepository.findAll()).thenReturn(List.of(p1,p2,p3,p4,p5,p6));

    }

    @Test
    public void findById_Fail(){
        Mockito.when(paymentsRepository.findById(123L)).thenReturn(Optional.empty());
        Assertions.assertThrows(PaymentNotfoundException.class, () -> {

            paymentsService.getPaymentById(123L);
        });

    }

    @Test
    public void testController(){
        Assertions.assertEquals(List.of("IRE", "CAN", "FRA", "USA"), countriesController.getCountries());
    }

    @Test
    public void testCountriesInService() {
        Assertions.assertEquals(List.of("IRE", "CAN", "FRA", "USA"), paymentsService.getCountries());

    }


}
