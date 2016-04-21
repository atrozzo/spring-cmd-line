package com.mdl.data.analyzer.alg.operations;

import java.util.List;

/**
 * This class calutalte the max value available in a list of Integers.
 *
 */
public class MLDMaxIntgerCalculator {
    final List<Integer> listToVerify;

    public MLDMaxIntgerCalculator(List<Integer> listToVerify) {
        this.listToVerify = listToVerify;
    }

    public Integer getMaxIntegersAvailable(){
        return  listToVerify.size();
    }




}
