package com.neueda.payments.control;

import com.neueda.payments.exceptions.PaymentNotfoundException;
import com.neueda.payments.model.Payment;
import com.neueda.payments.repositories.PaymentsRepository;
import com.neueda.payments.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/payment")
public class PaymentsController {
    private PaymentsService paymentsService;

    public PaymentsController(PaymentsService paymentsService){
        this.paymentsService = paymentsService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Payment> getAllPayments(@RequestParam(value = "country", required = false) String country,
                                        @RequestParam(value = "orderId", required = false) String orderId) {
        if (Objects.nonNull(country)){
            return paymentsService.getPaymentByCountry(country);
        } else if(Objects.nonNull(orderId)) {
            return paymentsService.getAllByOrderId(orderId);
        }
        return Collections.emptyList();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Payment getPaymentById(@PathVariable Long id) throws PaymentNotfoundException {
        return paymentsService.getPaymentById(id);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Payment savePayment(@RequestBody Payment payment){
        return paymentsService.savePayment(payment);
    }

}
