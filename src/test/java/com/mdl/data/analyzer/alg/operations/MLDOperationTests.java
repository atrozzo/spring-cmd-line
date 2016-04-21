package com.mdl.data.analyzer.alg.operations;

import com.mdl.data.analyzer.MLDApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Thsi class porvide test coverage for all teh operations.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MLDApp.class)

public class MLDOperationTests {

    private  List<Integer> listToVerify;

    @Before
    public void initData(){
        listToVerify = Arrays.asList(1, 200, 300 , 400, 300);
    }

    @Test
    public void shouldFindMostCommonInteger(){
        MLDMostCmnIntegerCalculator calculator =  new MLDMostCmnIntegerCalculator(listToVerify);
        Assert.notNull(calculator.getMostCommonInteger());
        Assert.isTrue(calculator.getMostCommonInteger() == 300);
    }


    @Test
    public void shouldFindAverageInteger(){
        Assert.notNull(new MLDAverageCalculator(listToVerify).getMeanValue());
        Assert.isTrue(new MLDAverageCalculator(listToVerify).getMeanValue().equals("240.2"));

    }

    @Test
    public void shouldFindTheTotalNumberOfInteger(){
        Assert.isTrue( new MLDMaxIntgerCalculator(listToVerify).getMaxIntegersAvailable()==5);
    }






}
