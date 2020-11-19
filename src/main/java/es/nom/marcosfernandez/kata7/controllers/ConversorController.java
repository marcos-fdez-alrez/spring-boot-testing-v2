package es.nom.marcosfernandez.kata7.controllers;

import es.nom.marcosfernandez.kata7.services.BestExchangeFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class which receives root requests.
 *
 */
@RestController
@RequestMapping("/convert/eur/usd")
public class ConversorController {

    /** USD Constant. */
    private static final String DEST_CURRENCY = "USD";

    /** bestExchangeFinder service injection. */
    @Autowired
    private BestExchangeFinder bestExchangeFinder;

    /**
     * REST API GET to convert an amount.
     * @param amount - Amount to be converted
     * @return Double - converted amount
     *
     */
    @GetMapping("/{amount}")
    public Double getBestConversionAmount(
            @PathVariable("amount") final double amount) {
        return amount * bestExchangeFinder.obtainBestRate(DEST_CURRENCY);
    }


}
