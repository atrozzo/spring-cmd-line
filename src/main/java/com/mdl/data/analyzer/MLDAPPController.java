package com.mdl.data.analyzer;

import com.mdl.data.analyzer.alg.MLDAlgorithm;
import com.mdl.data.analyzer.cmd.MLDCommand;
import com.mdl.data.analyzer.cmd.MLDExcutableCommands;
import com.mdl.data.analyzer.conf.*;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 *
 * The Controller has the duty of initialise the CommandLineRunner interface for spring boot and organise the commands and
 * the menu options available in the system and coming from the command line.
 *
 */

@Controller
public class MLDAPPController implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(MLDAPPController.class);

    @Autowired
    MLDMenuOptions menuOptions;

    @Autowired
    MLDMenuAlgorithm mldMenuAlgorithm;


    @Autowired
    ApplicationContext context;

    @Autowired
    @Qualifier("cmdLineParser")
    MLDCmdLineParser cmdLineParser;

    @Override
    public void run(String... strings) throws Exception {
        initCommands();
        initAlgorithms();
        parseCommandLineArguments(strings);
    }



    @Autowired
    private MLDExcutableCommands toExecute;

    /**
     * This method parse and validate the command line passed to the application.
     *
     * @param commandLineArguments
     */
    private void parseCommandLineArguments(@NotNull final String ... commandLineArguments){
        CommandLine commandLine;
        try
        {
            cmdLineParser.setMLDCmdLineParser(menuOptions, commandLineArguments,true);
            commandLine = cmdLineParser.getCmdCommandLine();

            for ( Option option : menuOptions.getOptions() )

				if (commandLine.hasOption(option.getLongOpt())) {
                    logger.debug("Adding command to execute : " + option.getLongOpt());
                    toExecute.setExecutableCommand(option.getLongOpt(), (MLDCommand) context.getBean(option.getLongOpt()));
				}
        }
        catch (ParseException parseException)
        {
            System.err.println(
                    "You have enetered an uknwon command "+ commandLineArguments.toString() );
            logger.error(parseException.getMessage());
        }
    }

    /**
     * This method discover alla available commands defined as beans.
     *
     */
    private  void initCommands() {
        MLDMenuOptions options = (MLDMenuOptions) context.getBean("MLDMenuOptions");
        String[] commandsBeanName = context.getBeanNamesForAnnotation(MLDWireOption.class);
        for (String currentBean : commandsBeanName) {
            MLDWireOption wiredOption = context.getBean(currentBean).getClass().getAnnotation(MLDWireOption.class);
            logger.debug("adding new option : " + wiredOption.value());
            options.addOption(new Option(wiredOption.shortValue(),
                    wiredOption.value(), wiredOption.mandatory(), wiredOption.description()));
        }
    }

    /**
     * This method retrieve all the available algorythm in the system at startup. ALl is available need to be annotated
     * with MLDWireAlg annotation.
     *
     */
    private void initAlgorithms(){
        Map<String,MLDAlgorithm>  algorithms = new HashMap<>();

        String[] commandsBeanName = context.getBeanNamesForAnnotation(MLDWireAlg.class);
        for (String currentBean : commandsBeanName){
            MLDWireAlg wiredName = context.getBean(currentBean).getClass().getAnnotation(MLDWireAlg.class);
            algorithms.put(wiredName.value(), (MLDAlgorithm)context.getBean(currentBean));
            logger.debug("adding new algorithm : " + wiredName.value());

        }
        mldMenuAlgorithm.setAlgorithms(algorithms);
    }
}
