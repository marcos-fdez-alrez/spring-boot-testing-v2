package es.nom.marcosfernandez.kata7.services;

import es.nom.marcosfernandez.kata7.model.Quote;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BestExchangeFinder {

    private static final List<String> conversors = Arrays.asList("https://api.frankfurter.app/latest",
            "https://api.ratesapi.io/api/latest",
            "https://api.exchangeratesapi.io/latest");

    public Double obtainBestRate(String currency) {
        return conversors.parallelStream().mapToDouble(conversor ->
            {
                try {
                    return CompletableFuture.supplyAsync(() -> obtainRateFromConversor(currency, conversor), executor)
                    //.completeOnTimeout(0D, 5, TimeUnit.SECONDS)
                            .get( 5, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException |  TimeoutException e) {
                    return 0D;
                }
            }).min().getAsDouble();
    }

    public Double obtainRateFromConversor(String currency, String conversor) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Quote> response = restTemplate.exchange(
                conversor, HttpMethod.GET, null,
                new ParameterizedTypeReference<Quote>(){});
        Optional<Quote> quote = Optional.ofNullable(response.getBody());
        return quote.stream().mapToDouble(q -> q.getRates().get(currency)).findFirst().getAsDouble();
    }

    private final Executor executor = Executors.newFixedThreadPool(conversors.size(), (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

}
