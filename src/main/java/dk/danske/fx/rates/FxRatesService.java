package dk.danske.fx.rates;

import dk.danske.fx.rates.provider.FxRatesException;
import dk.danske.fx.rates.provider.FxRatesProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FxRatesService {

    private final FxRatesProvider fxRatesProvider;

    public FxRatesService(FxRatesProvider fxRatesProvider) {
        this.fxRatesProvider = fxRatesProvider;
    }

    public FxRate fetchCurrentEurFxRate(Currencies foreignCurrency) throws FxRatesException {
        return fetchEurFxRate(LocalDate.now(), foreignCurrency);
    }

    public FxRate fetchEurFxRate(LocalDate date, Currencies foreignCurrency)
            throws FxRatesException {
        double rate = fetchRate(date, foreignCurrency);

        FxRate eurFxRate = new FxRate();
        eurFxRate.setCurrency(foreignCurrency);
        eurFxRate.setCurrencyLabel(currencyLabel(foreignCurrency));
        eurFxRate.setRate(rate);
        eurFxRate.setValueDateTime(LocalDateTime.now());
        return eurFxRate;
    }

    private double fetchRate(LocalDate date, Currencies foreignCurrency) throws FxRatesException {
        return fxRatesProvider.fetchRate(date, Currencies.EUR.toString(),
                foreignCurrency.toString());
    }

    private String currencyLabel(Currencies currencies) {
        if (currencies == Currencies.EUR) {
            return "Euro";
        }
        if (currencies == Currencies.DKK) {
            return "Danish krone";
        }
        if (currencies == Currencies.SEK) {
            return "Swedish krona";
        }
        return null;
    }

}
