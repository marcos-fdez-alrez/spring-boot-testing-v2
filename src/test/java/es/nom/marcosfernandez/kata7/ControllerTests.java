package es.nom.marcosfernandez.kata7;

import es.nom.marcosfernandez.kata7.controllers.ConversorController;
import es.nom.marcosfernandez.kata7.services.BestExchangeFinder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ControllerTests {

    private static final String DEST_CURRENCY = "USD";
    private static final Double BEST_RATE = 1.1234;
    private static final Double AMOUNT = 100D;

    @Mock
    private BestExchangeFinder bestExchangeFinder;

    @InjectMocks
    private ConversorController conversorController;

    @Test
    public void given_controller_when_call_convert_then_ok() {

        //When
        when(bestExchangeFinder.obtainBestRate(DEST_CURRENCY))
                .thenReturn(BEST_RATE);

        Double converted = conversorController.getBestConversionAmount(AMOUNT);

        //Then
        then(converted).isEqualTo(BEST_RATE * AMOUNT);
    }

}
