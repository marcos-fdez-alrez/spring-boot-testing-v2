package es.nom.marcosfernandez.kata7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

/**
 * Quote model class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    /** base property. */
    private String base;
    /** Map in which the conversion rates are hold. */
    private Map<String, Double> rates;
    /** Rates date. */
    private LocalDate date;

}
