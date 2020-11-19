package es.nom.marcosfernandez.kata7.services;

/**
 * Service interface.
 */
public interface BestExchangeFinder {

    /**
     * obtainBestRate method.
     * @param currency - String which contains the currency literal
     * @return Double - returns best found rate
     */
    Double obtainBestRate(String currency);

    /**
     * obtainRateFromConversorAPI method.
     * @param currency - String which contains the currency literal
     * @param conversor - String with the API URL
     * @return Double - returns found rate
     */
    Double obtainRateFromConversorAPI(String currency, String conversor);

}
