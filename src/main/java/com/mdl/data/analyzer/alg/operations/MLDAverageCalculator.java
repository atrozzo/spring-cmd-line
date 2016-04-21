package com.mdl.data.analyzer.alg.operations;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by angelo on 31/01/2016.
 */
public class MLDAverageCalculator {

    private final List<Integer> listToVerify;

    private String pattern = "###.##";

    public MLDAverageCalculator(List<Integer> listToVerify) {
        this.listToVerify = listToVerify;
    }

    /**
     * This method provide the mean value/average of the total of all the integers
     * @return
     */
    public String getMeanValue() {
        Double average = listToVerify.stream().mapToInt(val -> val).average().getAsDouble();
        return new DecimalFormat(pattern).format(average);

    }

}
