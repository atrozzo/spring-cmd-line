package com.mdl.data.analyzer.cmd.menu;

import com.mdl.data.analyzer.MLDStatusCodes;
import com.mdl.data.analyzer.cmd.MLDCommand;
import com.mdl.data.analyzer.cmd.MLDCommandLineResult;
import com.mdl.data.analyzer.cmd.MLDResult;
import com.mdl.data.analyzer.conf.MLDCmdLineParser;
import com.mdl.data.analyzer.conf.MLDMenuOptions;
import com.mdl.data.analyzer.conf.MLDWireOption;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@MLDWireOption(value = "help", description = ("show all available commands"),
		shortValue = ("h")) // the name of the menu is coming from the property as well the bean name.
@Service(value = "help")
public class MLDMenuHelp implements MLDCommand<MLDResult> {

    private final static Logger logger = LoggerFactory.getLogger(MLDMenuHelp.class);


    @Autowired
    @Qualifier("MLDMenuOptions")
    private MLDMenuOptions menuOptions;

    @Autowired
    @Qualifier("cmdLineParser")
    MLDCmdLineParser cmdLineParser;

    @Override
	public MLDCommandLineResult executeCmd() {



		return new MLDCommandLineResult.
				CmdResultBuilder(MLDStatusCodes.INFO.getCode(), "Help command").
				time(LocalDate.now()).dataResult(menuOptions.getTrasformedOptions()).build();
	}

    /**
     * This method provide a readable help for the output command line.
     *
     * @param options
     */
    private void printHelp(Options options) {


        logger.info("Command Line tool !!! ");
        logger.info("The Interface  provides an intelligent environment in which to run statistics against files.");
        logger.info("To build a module the README file attached");

        for (Option current : options.getOptions()){
            logger.info("Command : " + current.getLongOpt() + " = " + current.getDescription());

        }


    }


    @Override
    public void printResult() {
        printHelp(menuOptions);
    }

}
