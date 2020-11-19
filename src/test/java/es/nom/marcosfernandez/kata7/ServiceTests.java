package es.nom.marcosfernandez.kata7;

import es.nom.marcosfernandez.kata7.services.BestExchangeFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class ServiceTests {

    private static final String DEST_CURRENCY = "USD";
    private static final Double BEST_RATE = 1.1868;

    @Autowired
    private BestExchangeFinder bestExchangeFinder;

    @Test
    void when_USD_obtain_best_rate() {
        //Assertions.assertEquals(BEST_RATE,bestExchangeFinder.obtainBestRate(DEST_CURRENCY));
        then(bestExchangeFinder.obtainBestRate(DEST_CURRENCY)).isEqualTo(BEST_RATE);
    }
}
