package com.mdl.data.analyzer;

import com.mdl.data.analyzer.cmd.MLDCommand;
import com.mdl.data.analyzer.cmd.MLDExcutableCommands;
import com.mdl.data.analyzer.conf.MLDCmdLineParser;
import com.mdl.data.analyzer.conf.MLDMenuOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by angelo on 27/01/2016.
 */


@Service
public class MLDAppExecutor  {

    @Autowired
    MLDMenuOptions menuOptions;

    @Autowired
    ApplicationContext context;

    @Autowired
    @Qualifier("cmdLineParser")
    MLDCmdLineParser cmdLineParser;


    @Autowired
    MLDExcutableCommands executableCommands;

    /**
     * This method execute all the commands in the list. The first ot execute has to be however the file/resource loader.
     *
     */
    public void executeCommands(){
        executableCommands.getExecutableCommands().forEach((k,v)->{
            v.executeCmd();
            v.printResult();
        });


    }


}
