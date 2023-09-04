package dk.danske.fx.rates;

import dk.danske.fx.rates.provider.FxRatesException;
import dk.danske.fx.rates.provider.FxRatesProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FxRatesServiceTest {

    @Mock
    private FxRatesProvider fxRatesProvider;
    @InjectMocks
    private FxRatesService fxRatesService;

    @Test
    void shouldFetchEurFxRateFromDkkSuccessfully() throws FxRatesException {
        try (MockedStatic<LocalDateTime> utilities = mockStatic(LocalDateTime.class)) {
            LocalDateTime expectedLocalDateTime = LocalDateTime.now();
            utilities.when(LocalDateTime::now).thenReturn(expectedLocalDateTime);
            LocalDate fetchDate = LocalDate.of(2023, 9, 4);
            Currencies expectedCurrency = Currencies.DKK;
            String expectedCurrencyLabel = CurrencyLabels.DANISH_KRONE;
            double expectedRate = 7.45d;
            when(fxRatesProvider.fetchRate(fetchDate, Currencies.EUR.toString(),
                    Currencies.DKK.toString())).thenReturn(expectedRate);

            FxRate actualFxRate = fxRatesService.fetchEurFxRate(fetchDate, expectedCurrency);

            assertThat(actualFxRate.getRate()).isEqualTo(expectedRate);
            assertThat(actualFxRate.getCurrency()).isEqualTo(expectedCurrency);
            assertThat(actualFxRate.getCurrencyLabel()).isEqualTo(expectedCurrencyLabel);
            assertThat(actualFxRate.getValueDateTime()).isEqualTo(expectedLocalDateTime);
        }
    }
}
