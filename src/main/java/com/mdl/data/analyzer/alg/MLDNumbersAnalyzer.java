package com.mdl.data.analyzer.alg;

import com.mdl.data.analyzer.MLDStatusCodes;
import com.mdl.data.analyzer.alg.operations.MLDAverageCalculator;
import com.mdl.data.analyzer.alg.operations.MLDMaxIntegerPerLineCalculator;
import com.mdl.data.analyzer.alg.operations.MLDMostCmnIntegerCalculator;
import com.mdl.data.analyzer.alg.operations.MLDTotIntgersCalculator;
import com.mdl.data.analyzer.cmd.MLDCommandLineResult;
import com.mdl.data.analyzer.cmd.MLDResult;
import com.mdl.data.analyzer.conf.MLDWireAlg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class has a specific implementation for coalculating:
 * 1. Total number of integers
 * 2. Mean value of all integers (to three decimal places)
 * 3. Highest number of integers in a single line
 * 4. Most common integer
 *
 * All the result will be reported in the MLDResult
 *
 */

@MLDWireAlg(value = "integers", description = ("collect infomations abou integers"))
@Service(value = "analyze-integers")
public class MLDNumbersAnalyzer implements MLDAlgorithm<MLDResult> {
    private final static Logger logger = LoggerFactory.getLogger(MLDNumbersAnalyzer.class);


    private String fileSeparator = ";";
    private final int minIntgervalue = -1000;
    private final int maxIntgervalue = 1000;


    private BufferedReader reader;
    private List<Integer> toAnalyze;
    private MLDResult mldResult;
    private Map<String, String> operationResults = new HashMap();
    private Map<Integer, List<Integer>> integersPerLine;
    @Override
    public MLDResult calculate(final BufferedReader reader) {
        if (reader == null)
            throw new IllegalArgumentException("File reader its null");

        setValuesToAnalyze(reader);
        operationResults.put("ToT-Number-Integers","[" +new MLDTotIntgersCalculator(toAnalyze).getTotAmountIntgers()+"]");
        operationResults.put("Most-Common-Integer","["+ new MLDMostCmnIntegerCalculator(toAnalyze).getMostCommonInteger()+"]");
        operationResults.put("ToT-MeanValue", new MLDAverageCalculator(toAnalyze).getMeanValue());

        setMaxIntPerlIneResult();

        mldResult = new MLDCommandLineResult.CmdResultBuilder(MLDStatusCodes.COMPLETED.getCode(), "Analyzer command executed.").
                time(LocalDate.now()).dataResult(operationResults).build();

        return mldResult;
    }


    /**
     * Read the CSV and set the values in the expected format for the calculation .
     *
     */
    private void setValuesToAnalyze(final BufferedReader reader){
        try {
            integersPerLine = getAllAvailableIntegersPerLine(reader);
            toAnalyze = getAllAvailableIntegers(integersPerLine);
            logger.info("CSV File loaded.");
        } catch (IOException e) {
            logger.error("Something went wrong ... !!!");
            e.printStackTrace();
        }

    }

    /**
     * The method parse and reorganise the result for the print output.
     *
     */
    private void setMaxIntPerlIneResult(){
        MLDMaxIntegerPerLineCalculator calculator =  new MLDMaxIntegerPerLineCalculator(integersPerLine);
        Map<Integer, Integer> res = calculator.getHighestIntegerPerLine();
        if (res != null && res.size()>0){
            Iterator it = res.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry current = (Map.Entry)it.next();
                operationResults.put("Highest Integer at Line  " + current.getKey(), String.valueOf(current.getValue()));
            }
        }
    }


    /**
     * This method retrieve all the available Integers devided by line number.
     * Line 1 : int1 , int2 , int3 ...
     *
     * @param reader BufferedReader fo the CSV file to load
     * @return a Map of List of Integer.
     * @throws IOException  in case of the file is not accessible anymore or corrupted.
     */
    public Map<Integer,List<Integer>> getAllAvailableIntegersPerLine(BufferedReader reader) throws IOException {
        Map<Integer,List<Integer>> allLInes = new HashMap<>();
        String line;
        int counter = 1;
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(fileSeparator);
            List<Integer> intValuesPerLine = new ArrayList<>(columns.length);
            for (String currentValue : columns){
                if (validate(currentValue)){
                    intValuesPerLine.add(new Integer(currentValue));
                }
            }
            if (intValuesPerLine.size() >0)
                allLInes.put(counter++,intValuesPerLine);

        }
        return allLInes;
    }

    /**
     * This method convert a Map in a full list of integers
     *
     * @param toCovert
     * @return List
     */
    public List<Integer> getAllAvailableIntegers(Map<Integer, List<Integer>> toCovert){
            return  toCovert.entrySet().stream()
                .map(Map.Entry::getValue).flatMap(l ->l.stream())
                .collect(Collectors.toList());
    }



    /**
     * The method cast and validate the String to Integer if is possible.
     *
     * @param c the string to verify and cast.
     * @return true if the integer can be added to the list.
     */
    private boolean validate (String c){
        int result;
        try {
            result = Integer.parseInt(c);
        } catch (NumberFormatException e) {
            return false;
        }
        return isIntegerInRage(result,minIntgervalue,maxIntgervalue);
    }

    /**
     * The method verify that a int it s in the range
     * @param x to verify
     * @param min min value
     * @param max max value
     * @return true if the integer is in range.
     */
    private  boolean isIntegerInRage(int x, int min, int max)
    {
        return x>=min && x<=max;
    }




}
