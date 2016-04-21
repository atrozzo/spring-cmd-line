package com.mdl.data.analyzer.alg.operations;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * Created by angelo on 29/01/2016.
 */
public class MLDMostCmnIntegerCalculator {

    private final List<Integer> listToVerify;

    public MLDMostCmnIntegerCalculator(List<Integer> listToVerify){
        this.listToVerify = listToVerify;
    }


    /**
     * The method verify the most common used Integer
     *
     * @return Integer that represent the  most common intger found.
     */
    public Integer getMostCommonInteger() {
        return  listToVerify.stream()
                .reduce(BinaryOperator.maxBy((o1, o2) -> Collections.frequency(listToVerify, o1) -
                        Collections.frequency(listToVerify, o2))).orElse(null);
    }

}
