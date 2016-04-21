package com.mdl.data.analyzer.alg.operations;

import java.util.List;

/**
 * Created by angelo on 31/01/2016.
 */
public class MLDTotIntgersCalculator {

    private final List<Integer> listToVerify;

    public MLDTotIntgersCalculator(List<Integer> listToVerify) {
        this.listToVerify = listToVerify;
    }


    public int getTotAmountIntgers(){
        return listToVerify.size();
    }
}
