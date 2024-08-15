package com.neueda.payments.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PaymentNotfoundException extends Exception{
    public PaymentNotfoundException(String message){
        super(message);
    }
}
