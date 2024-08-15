package com.neueda.payments.service;

import com.neueda.payments.exceptions.PaymentNotfoundException;
import com.neueda.payments.model.Payment;
import com.neueda.payments.repositories.PaymentsRepository;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    private PaymentsRepository paymentsRepository;

    public PaymentsServiceImpl(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentsRepository.findAll();
    }



    @Override
    public Payment save(Payment payment) {
        return paymentsRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) throws PaymentNotfoundException {
        return paymentsRepository.findById(id).orElseThrow(() -> new PaymentNotfoundException("No payment with Id:" + id));
    }

    @Override
    public List<Payment> getPaymentByCountry(String country) {
        return paymentsRepository.getPaymentByCountry(country);
    }

    @Override
    public List<Payment> getAllByOrderId(String orderId) {
        return paymentsRepository.findAllByOrderId(orderId);
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentsRepository.save(payment);
    }

    @Override
    public List<String> getCountries() {
        return paymentsRepository.findAll().stream().map(Payment::getCountry).distinct().sorted().toList();
    }

}
