package com.lux.lms.processor;

import com.lux.lms.dataModel.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stasico on 19.04.2015.
 */

@Service("processing")
public class ProcessingImpl implements Processing {

    private static final Logger logger = Logger.getLogger(ProcessingImpl.class);

    private static final BigDecimal HUNDRED = new BigDecimal("100");

    private static final BigDecimal ALLOWED_EXCESS = new BigDecimal("1.05");

    private List<ExceedingData> exceedingDataList = new ArrayList<ExceedingData>();

    public List<ExceedingData> findExceeding(AggregatedData aggregatedData) {
        logger.debug("starting findExceeding method ");
        List<LimitsData> limitsDataList = aggregatedData.getLimits();
        List<OperationsData> operationsDataList = aggregatedData.getOperations();

        for (LimitsData limit : limitsDataList) {
            BigDecimal limitValue = limit.getValue();
            if (limitValue.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            String limitCountry = limit.getCountry();
            String limitCurrency = limit.getCurrency();
            for (OperationsData operation : operationsDataList) {
                String operationCountry = operation.getCountry();
                String operationCurrency = operation.getCurrency();
                if (limitCountry.equals(operationCountry) && limitCurrency.equals(operationCurrency)) {
                    long operationId = operation.getIdOperation();
                    BigDecimal operationValue = operation.getAmount();
                    logger.debug("limit row matches to operation line with ID" + operationId);
                    checkOverLimitAndPutExcessToDataList(operationId, operationValue, limitValue);
                }
            }
        }
        return exceedingDataList;
    }

    public BigDecimal checkOverLimitAndPutExcessToDataList(long operationID, BigDecimal operationValue, BigDecimal limitValue ) {
        logger.debug("starting checkOverLimitAndPutExcessToDataList method");

        int operationCompareResult = operationValue.compareTo(limitValue.multiply(ALLOWED_EXCESS));
        if (operationCompareResult == 1) {
            BigDecimal overLimitPercentage = HUNDRED.multiply(operationValue).divide(limitValue);
            exceedingDataList.add(new ExceedingData(operationID, overLimitPercentage));
            logger.debug("excess detected on " + operationID + "ID with" + operationValue + "operation value and " + limitValue + "limit value");
        }
        return operationValue.divide(limitValue);
    }

}