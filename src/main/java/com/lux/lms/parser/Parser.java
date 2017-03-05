package com.lux.lms.parser;

import com.lux.lms.dataModel.AggregatedData;
import com.lux.lms.dataModel.LimitsData;
import com.lux.lms.dataModel.OperationsData;

import java.io.InputStream;

public interface Parser {

    AggregatedData simpleParser(InputStream inputStream);

    LimitsData limitsParsing(String[] splitLine);

    OperationsData operationsParsing(String[] splitLine);

    boolean checkLimit(String limit);

    boolean checkOperation(String operation);
}
