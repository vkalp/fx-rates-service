package dk.danske.fx.rates.provider;

import java.time.LocalDate;

/**
 * Interface that represents FX Rates retrieval service.
 */
public interface FxRatesProvider {

    /**
     * Returns exchange rate of the domestic currency against foreign currency.
     *
     * @param date             exchange rate date
     * @param domesticCurrency domestic currency code e.g. SEK
     * @param foreignCurrency  foreign currency code e. DKK
     * @return exchange rate
     * @throws FxRatesException thrown if fx rate cannot be fetched due to some reason
     */
    double fetchRate(LocalDate date, String domesticCurrency,
                     String foreignCurrency) throws FxRatesException;


}
