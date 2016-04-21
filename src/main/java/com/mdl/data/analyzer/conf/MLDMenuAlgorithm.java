package com.mdl.data.analyzer.conf;

import com.mdl.data.analyzer.alg.MLDAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Thsi class contains the information about the menu option 
 * 
 * @author angelo
 *
 */


@Component(value = "MLDMenuAlgorithm")
public class MLDMenuAlgorithm {


    public MLDMenuAlgorithm(){
    }


    private Map<String,MLDAlgorithm>  algorithms = new HashMap<>();


    /**
     * Thsi return all the available algorithms registered in the system at startup.
     *
     * @return Map
     */
    public Map<String,MLDAlgorithm> getAlgorithms(){
        return algorithms;
    }

   public void setAlgorithms(Map<String, MLDAlgorithm> algorithms){
       this.algorithms = algorithms;
   }




}
