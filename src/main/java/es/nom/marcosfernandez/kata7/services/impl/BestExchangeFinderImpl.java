package es.nom.marcosfernandez.kata7.services.impl;

import es.nom.marcosfernandez.kata7.model.Quote;
import es.nom.marcosfernandez.kata7.services.BestExchangeFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


/**
 * Service Implementation Class.
 */
@Slf4j
@Service
public class BestExchangeFinderImpl implements BestExchangeFinder {

    /** TIMEOUT CONSTANT. */
    private static final Integer TIMEOUT = 5;
    /** URLs APIs. */
    private static final List<String> CONVERSORS =
            Arrays.asList("https://api.frankfurter.app/latest",
                            "https://api.ratesapi.io/api/latest",
                            "https://api.exchangeratesapi.io/latest");

    /**
     *
     * obtainBestRate method.
     * @param currency - String which contains the currency literal
     * @return Double - returns best found rate
     *
     */
    public Double obtainBestRate(final String currency) {
        return CONVERSORS.parallelStream()
                .map(conversor -> obtainAsyncRate(currency, conversor))
                .mapToDouble(CompletableFuture::join)
                .min().getAsDouble();
    }

    /**
     *
     * obtainAsyncRate method.
     * @param currency - String which contains the currency literal
     * @param conversor - String with the API URL
     * @return Double - returns found rate
     *
     */
    private CompletableFuture<Double> obtainAsyncRate(final String currency,
                                   final String conversor) {
//      try {
        return CompletableFuture.supplyAsync(
            () -> obtainRateFromConversorAPI(currency, conversor), executor)
                .orTimeout(TIMEOUT, TimeUnit.SECONDS)
                .handle((response, ex) -> {
                    if (!Objects.isNull(ex)) {
                        return 0D;
                    }
                    return response;
                });
                //.get(TIMEOUT, TimeUnit.SECONDS);
            //.completeOnTimeout(0D, TIMEOUT, TimeUnit.SECONDS)
//  } catch (InterruptedException | ExecutionException | TimeoutException e) {
//      return 0D;
//  }
    }

    /**
     *
     * obtainRateFromConversorAPI method.
     * @param currency - String which contains the currency literal
     * @param conversor - String with the API URL
     * @return Double - returns found rate
     *
     */
    public Double obtainRateFromConversorAPI(final String currency,
                                             final String conversor) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Quote> response = restTemplate.exchange(
                                conversor, HttpMethod.GET, null,
                                new ParameterizedTypeReference<Quote>() { });
        Optional<Quote> quote = Optional.ofNullable(response.getBody());
        return quote.stream().mapToDouble(q -> q.getRates()
                .get(currency)).findFirst().getAsDouble();
    }

    /**
     *
     * ThreadPoolExecutor.
     *
     */
    private final Executor executor =
            Executors.newFixedThreadPool(CONVERSORS.size(), (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

}
