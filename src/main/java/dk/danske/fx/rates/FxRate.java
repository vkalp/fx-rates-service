package dk.danske.fx.rates;

import java.time.LocalDateTime;

public class FxRate {

    private Currencies currencies;

    private String currencyLabel;

    private Double rate;

    private LocalDateTime valueDateTime;

    public Currencies getCurrency() {
        return currencies;
    }

    public void setCurrency(Currencies currencies) {
        this.currencies = currencies;
    }

    public String getCurrencyLabel() {
        return currencyLabel;
    }

    public void setCurrencyLabel(String currencyLabel) {
        this.currencyLabel = currencyLabel;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDateTime getValueDateTime() {
        return valueDateTime;
    }

    public void setValueDateTime(LocalDateTime valueDateTime) {
        this.valueDateTime = valueDateTime;
    }
}
