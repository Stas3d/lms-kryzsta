package com.lux.lms.processor;

import com.lux.lms.dataModel.AggregatedData;
import com.lux.lms.dataModel.ExceedingData;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Stasico on 22.04.2015.
 */
public interface Processing {

    BigDecimal checkOverLimitAndPutExcessToDataList(long operationID, BigDecimal operationValue, BigDecimal limitValue);

    List<ExceedingData> findExceeding(AggregatedData aggregatedData);
}
