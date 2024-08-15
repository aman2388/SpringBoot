package com.neueda.payments;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.payments.model.Payment;
import com.neueda.payments.repositories.PaymentsRepository;
import com.neueda.payments.repositories.UsersRepository;
import com.neueda.payments.service.BootstrapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@AutoConfigureMockMvc
public class IntegrationTests {

    @MockBean
    private PaymentsRepository paymentsRepository;

    @MockBean
    private UsersRepository userRepository;

    @MockBean
    BootstrapService bootstrapService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testThatPostingANewPaymentIsSavedToTheDB() throws Exception {
        Payment payment = new Payment();
        payment.setOrderId("1234");
        payment.setAmount(17.33);
        payment.setCountry("IRL");
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(payment);

        mockMvc.perform(post("/api/payment")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk());

        ArgumentCaptor<Payment> capturedPayment = ArgumentCaptor.forClass(Payment.class);

        Mockito.verify(paymentsRepository).save(capturedPayment.capture());
        Payment receivedPayment = capturedPayment.getValue();

        assertEquals("1234", receivedPayment.getOrderId());


    }
}
