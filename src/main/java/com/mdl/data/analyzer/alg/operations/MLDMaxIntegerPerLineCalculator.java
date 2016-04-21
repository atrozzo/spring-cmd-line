package com.mdl.data.analyzer.alg.operations;

import java.util.*;
import java.util.stream.Stream;

/**
 *
 * Single finctionality class, that provide infomation about
 * how many integer per line are available in a map.
 *
 */
public class MLDMaxIntegerPerLineCalculator {

    private final Map<Integer, List<Integer>> toAnalyze;

    public MLDMaxIntegerPerLineCalculator(Map<Integer, List<Integer>> toAnalyze) {
        this.toAnalyze = toAnalyze;
    }


    /**
     * The method retrieve the highest integer for each line. A line it s represente in the Map by a List of integer.
     *
     * @return Map where the key it s the line number and the value the highest value in the line.
     */
    public Map<Integer,Integer> getHighestIntegerPerLine(){
        Map<Integer, Integer> res = new HashMap<>();
        Iterator it = toAnalyze.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry current = (Map.Entry)it.next();
            res.put((Integer) current.getKey(),Collections.max((List<Integer>)current.getValue()));
        }
        return res;
    }


}
