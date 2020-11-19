package es.nom.marcosfernandez.kata7;

import es.nom.marcosfernandez.kata7.services.BestExchangeFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.stream.Stream;

@SpringBootTest
class ApplicationTests {

    private static final Stream<String> conversors = Stream.of("https://api.frankfurter.app/latest",
            "https://api.ratesapi.io/api/latest",
            "https://api.exchangeratesapi.io/latest");

    private static final String DEST_CURRENCY = "USD";
    private static final Double BEST_RATE = 1.1868;

    @Autowired
    private BestExchangeFinder bestExchangeFinder;

    @Test
    void when_USD_obtain_best_rate() {
        Assertions.assertEquals(BEST_RATE,bestExchangeFinder.obtainBestRate(DEST_CURRENCY));
    }
}
