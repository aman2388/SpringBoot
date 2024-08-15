package com.neueda.payments.control;

import com.neueda.payments.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/country")
public class CountriesController {
    @Autowired
    private PaymentsService paymentsService;

    @GetMapping()
    public List<String> getCountries(){
        return paymentsService.getCountries();
    }
}
