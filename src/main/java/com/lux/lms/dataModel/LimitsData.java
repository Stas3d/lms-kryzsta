package com.lux.lms.dataModel;

import java.math.BigDecimal;

/**
 * Created by Stasico on 22.04.2015.
 */
public class LimitsData {

    private String country;
    private String currency;
    private BigDecimal value;

    public LimitsData(String country, String currency, BigDecimal value) {
        this.country = country;
        this.currency = currency;
        this.value = value;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }

}

