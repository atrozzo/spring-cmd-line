package com.mdl.data.analyzer.conf;

import com.mdl.data.analyzer.cmd.MLDExcutableCommands;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by angelo on 27/01/2016.
 */


@Configuration
public class MLDCfg {

    @Bean(name = "cmdLineParser")
    public MLDCmdLineParser getCmdCommandLineParser(){
        return new MLDCmdLineParser();
    }

    @Bean(name= "MLDExcutableCommands" )
    public MLDExcutableCommands getMldaCommands(){
        return new MLDExcutableCommands();
    }

    /*@Bean (name="MLDFileReaderUtil")
    public MLDFileReaderUtil getMldFileReaderUtil(){
        return new
    }*/



}
