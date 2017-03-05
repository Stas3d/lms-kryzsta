package com.lux.lms.dataModel;

import java.math.BigDecimal;

public class ExceedingData {

    private long idOperation;
    private BigDecimal amount;

    public ExceedingData(long idOperation, BigDecimal amount) {
        this.idOperation = idOperation;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "\n" +
                "idOperation=" + idOperation +
                ", amount=" + amount + "%";
    }
}
