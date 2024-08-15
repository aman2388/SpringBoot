package com.neueda.payments;

import com.neueda.payments.model.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityTests {

    @Test
    public void testEqualityOfPaymentsUsingIdOnly(){
        Payment p1 = new Payment();
        p1.setId(17L);
        Payment p2 = new Payment();
        p2.setId(17L);
        Assertions.assertEquals(p1, p2);
    }
}
