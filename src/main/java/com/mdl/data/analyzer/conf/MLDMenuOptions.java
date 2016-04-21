package com.mdl.data.analyzer.conf;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Thsi class contains the information about the menu option 
 * 
 * @author angelo
 *
 */


@Component(value = "MLDMenuOptions")
public class MLDMenuOptions extends Options{

    private final static Logger logger = LoggerFactory.getLogger(MLDMenuOptions.class);

    @Autowired
    private ApplicationContext applicationContext;

    public MLDMenuOptions(){
        //init();
    }

    public Map<String, String> getTrasformedOptions(){
        Map<String, String> opt = new HashMap<>();
        for ( Option option : getOptions()){
            opt.put(option.getOpt(), option.getValue());
        }
        return opt;
    }


    private void init(){
        String[] commandsBeanName = applicationContext.getBeanNamesForAnnotation(MLDWireOption.class);
        for (String currentBean : commandsBeanName){
            MLDWireOption wiredOption = applicationContext.getBean(currentBean).getClass().getAnnotation(MLDWireOption.class);
            logger.debug("adding new option : " + wiredOption.value());
            this.addOption(new Option(wiredOption.shortValue(),
                    wiredOption.value(), wiredOption.mandatory(), wiredOption.description()));
        }
    }


}
