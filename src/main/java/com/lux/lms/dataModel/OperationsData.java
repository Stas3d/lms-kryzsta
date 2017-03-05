package com.lux.lms.dataModel;

import java.math.BigDecimal;

/**
 * Created by Stasico on 22.04.2015.
 */
public class OperationsData {

    private long idOperation;
    private String country;
    private String currency;
    private BigDecimal amount;

    public OperationsData(long idOperation, String country, String currency, BigDecimal amount) {
        this.country = country;
        this.idOperation = idOperation;
        this.currency = currency;
        this.amount = amount;
    }

    public String getCountry() {
        return country;
    }

    public long getIdOperation() {
        return idOperation;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
