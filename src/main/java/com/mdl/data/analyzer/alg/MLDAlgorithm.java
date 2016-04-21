package com.mdl.data.analyzer.alg;

import java.io.BufferedReader;

/**
 * Common interface for all algorithms
 */
public interface MLDAlgorithm <T>{

    T calculate(BufferedReader reader);
}
