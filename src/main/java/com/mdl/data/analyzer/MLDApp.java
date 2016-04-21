package com.mdl.data.analyzer;

import com.mdl.data.analyzer.conf.MLDMenuOptions;
import com.mdl.data.analyzer.conf.MLDWireOption;
import org.apache.commons.cli.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@SpringBootApplication
public class MLDApp  {
    
    private final static Logger logger = LoggerFactory.getLogger(MLDApp.class);

    private static ApplicationContext context;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}


    public static void main(String[] args) throws Exception {
         context =  SpringApplication.run(MLDApp .class, args);
		((MLDAppExecutor)context.getBean("MLDAppExecutor")).executeCommands();
	}





}
