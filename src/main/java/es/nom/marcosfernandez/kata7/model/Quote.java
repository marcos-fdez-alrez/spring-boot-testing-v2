package es.nom.marcosfernandez.kata7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    private String base;
    private Map<String,Double> rates;
    private LocalDate date;

}
