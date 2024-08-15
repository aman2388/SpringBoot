package com.neueda.payments.service;

import com.neueda.payments.exceptions.PaymentNotfoundException;
import com.neueda.payments.model.Payment;

import java.util.*;

public interface PaymentsService {

    List<Payment> getAllPayments();

    Payment save(Payment payment);

    Payment getPaymentById(Long id) throws PaymentNotfoundException;

    List<Payment> getPaymentByCountry(String country);

    List<Payment> getAllByOrderId(String orderId);

    Payment savePayment(Payment payment);

    List<String> getCountries();
}
