package com.neueda.payments;

import com.neueda.payments.aspects.LoggingAspect;
import com.neueda.payments.aspects.RepoLogginAspect;
import com.neueda.payments.control.CountriesController;
import com.neueda.payments.exceptions.PaymentNotfoundException;
import com.neueda.payments.model.Payment;
import com.neueda.payments.repositories.PaymentsRepository;
import com.neueda.payments.service.BootstrapService;
import com.neueda.payments.service.PaymentsService;
import com.neueda.payments.service.UserService;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ServiceClassTests {

    @Autowired
    PaymentsService paymentsService;

    @MockBean
    private PaymentsRepository paymentsRepository;

    @MockBean
    private UserService userService;

    @MockBean
    BootstrapService bootstrapService;

    @Autowired
    CountriesController countriesController;

    @MockBean
    private RepoLogginAspect repoLoggingAspect;

    @MockBean
    private LoggingAspect serviceLoggingAspect;


    @BeforeEach
    public void setup() {
        Mockito.when(paymentsRepository.findById(123L)).thenReturn(Optional.empty());

        Payment p1 = new Payment();
        p1.setCountry("CAN");

        Payment p2 = new Payment();
        p2.setCountry("USA");

        Payment p3 = new Payment();
        p3.setCountry("IRL");

        Payment p4 = new Payment();
        p4.setCountry("FRA");

        Payment p5 = new Payment();
        p5.setCountry("FRA");

        Payment p6 = new Payment();
        p6.setCountry("CAN");

        Mockito.when(paymentsRepository.findAll()).thenReturn(List.of(p1,p2,p3,p4,p5,p6));
    }

    @Test
    public void testThatNoMatchingPaymentReturnsAnError() {
        assertThrows(PaymentNotfoundException.class, () -> {
            paymentsService.getPaymentById(123L);
        });
    }


    @Test
    public void testCountries(){
        assertEquals(List.of("CAN", "FRA", "IRL", "USA"), paymentsService.getCountries());
    }

    @Test
    public void testController(){
        assertEquals(List.of("CAN", "FRA", "IRL", "USA"), countriesController.getCountries());
    }

}
