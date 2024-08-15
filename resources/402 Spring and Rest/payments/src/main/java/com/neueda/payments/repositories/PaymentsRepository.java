package com.neueda.payments.repositories;

import com.neueda.payments.model.Payment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long> {

    public List<Payment> getPaymentByCountry(String country);

    public List<Payment> findAllByOrderId(String orderId);
}
