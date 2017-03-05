package com.lux.lms.dataModel;

import java.util.List;

/**
 * Created by Stasico on 20.04.2015.
 */
public class AggregatedData {

    private List<LimitsData> limits;
    private List<OperationsData> operations;

    public AggregatedData(List<LimitsData> limits, List<OperationsData> operations) {
        this.limits = limits;
        this.operations = operations;
    }

    public List<LimitsData> getLimits() {
        return limits;
    }

    public List<OperationsData> getOperations() {
        return operations;
    }
}
