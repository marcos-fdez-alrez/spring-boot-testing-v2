package es.nom.marcosfernandez.kata7.controllers;

import es.nom.marcosfernandez.kata7.services.BestExchangeFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convert/eur/usd")
public class ConversorController {

    private static final String DEST_CURRENCY = "USD";

    @Autowired
    private BestExchangeFinder bestExchangeFinder;

    @GetMapping("/{amount}")
    public Double getBestConversionAmount(@PathVariable("amount") final double amount) {
        return amount * bestExchangeFinder.obtainBestRate(DEST_CURRENCY);
    }


}
