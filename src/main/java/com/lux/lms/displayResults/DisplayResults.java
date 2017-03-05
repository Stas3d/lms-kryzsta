package com.lux.lms.displayResults;

import com.lux.lms.dataModel.ExceedingData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Stasico on 21.05.2015.
 */

@Component("display")
public class DisplayResults {

    public DisplayResults() {
    }

    public void displayMethod(List<ExceedingData> exceedingDataList) {

        if (exceedingDataList == null || exceedingDataList.isEmpty()) {
            System.out.println("no exceeding");
        } else {
            for (ExceedingData exceedingData : exceedingDataList) {
                System.out.println(exceedingData.toString());
            }
        }
    }
}
