package com.lux.lms.parser;

import com.lux.lms.dataModel.AggregatedData;
import com.lux.lms.dataModel.LimitsData;
import com.lux.lms.dataModel.OperationsData;
import com.lux.lms.exception.ParserException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Stasico on 19.04.2015.
 */


@Service("parser")
public class ParserImpl implements Parser {

    private static final Logger logger = Logger.getLogger(ParserImpl.class);

    private List<LimitsData> limitsDataList = new ArrayList<LimitsData>();

    private List<OperationsData> operationsDataList = new ArrayList<OperationsData>();

    @Value("${limitRegExpPattern}")
    private String limitPattern;

    @Value("${operationRegExpPattern}")
    private String operationPattern;

    public ParserImpl() {
    }

    public AggregatedData simpleParser(InputStream inputStream) {
        if (inputStream == null) {
            logger.warn("InputStream is null");
            return null;
        }

        AggregatedData aggregate = new AggregatedData(limitsDataList, operationsDataList);
        Scanner inputData = new Scanner(inputStream);
        logger.info("Scanner instance created");
        while (inputData.hasNext()) {
            String line = inputData.nextLine();
            String[] splitLine = line.split(" ");
            if (splitLine.length == 3) {
                logger.debug("The row " + splitLine + "was identified as Limit");
                checkLimit(line);
                limitsParsing(splitLine);
            } else if (splitLine.length == 4) {
                logger.debug("The row " + splitLine + "was identified as Operation");
                checkOperation(line);
                operationsParsing(splitLine);
            }
            logger.debug("non-data line detected" + splitLine);
        }
        logger.debug("ready to return aggregate instance");
        return aggregate;
    }

    public LimitsData limitsParsing(String[] splitLine) {
        String country = splitLine[0];
        String currency = splitLine[1];
        BigDecimal amount = new BigDecimal(splitLine[2]);

        logger.debug("creating new Limits Data instance using " + splitLine);
        LimitsData limitsData = new LimitsData(country, currency, amount);
        limitsDataList.add(limitsData);
        return limitsData;
    }

    public OperationsData operationsParsing(String[] splitLine) {
        long idOperation = Long.valueOf(splitLine[0]);
        String country = splitLine[1];
        String currency = splitLine[2];
        BigDecimal amount = new BigDecimal(splitLine[3]);

        logger.debug("creating new Operations Data instance using " + splitLine);
        OperationsData operationsData = new OperationsData(idOperation, country, currency, amount);
        operationsDataList.add(operationsData);
        return operationsData;
    }

    public boolean checkLimit(String limit) {
        String limitPattern = "([a-zA-Z]\\s*)+\\s[A-Z]{3}\\s\\d+(\\.\\d+)?";
        if (!limit.matches(limitPattern)) {
            logger.warn("Limits input data error warning while checking  " + limit);
            throw new ParserException("Limits input data error");
        }
        return true;
    }

    public boolean checkOperation(String operation) {
        String operationPattern = "\\d+\\s([a-zA-Z]\\s*)+\\s[A-Z]{3}\\s\\d+(\\.\\d+)?";
        if (!operation.matches(operationPattern)) {
            logger.warn("Operations input data error warning while checking  " + operation);
            throw new ParserException("Operations input data error");
        }
        return true;
    }
}
