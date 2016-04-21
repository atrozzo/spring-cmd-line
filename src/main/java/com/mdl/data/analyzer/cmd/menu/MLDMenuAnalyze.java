package com.mdl.data.analyzer.cmd.menu;

import com.mdl.data.analyzer.MLDStatusCodes;
import com.mdl.data.analyzer.cmd.MLDCommand;
import com.mdl.data.analyzer.cmd.MLDCommandLineResult;
import com.mdl.data.analyzer.cmd.MLDResult;
import com.mdl.data.analyzer.conf.*;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * This class it s the access point ot any possible algorythm that  need t be executed.
 *
 *
 */


@MLDWireOption(value = "analyze", description = ("apply an statistic algorithm to the content file"),
        shortValue = ("a")) // the name of the menu is coming from the property as well the bean name.
@Service(value = "analyze")
public class MLDMenuAnalyze  implements MLDCommand<MLDResult> {

    private final static Logger logger = LoggerFactory.getLogger(MLDMenuAnalyze.class);


    @Autowired
    @Qualifier("cmdLineParser")
    MLDCmdLineParser cmdLineParser;

    @Autowired
    @Qualifier("MLDMenuOptions")
    private MLDMenuOptions menuOptions;

    @Autowired
    @Qualifier("MLDMenuAlgorithm")
    private MLDMenuAlgorithm mldMenuAlorithm;

    @Autowired
    ApplicationContext context;

    private String[] algorithms;
   // private MLDCommandLineResult result;
    private String fileName;

    private BufferedReader reader;

    private List<MLDResult> calculationsResults = new ArrayList<>();

    @Override
    public MLDResult executeCmd() {
        try {
            fileName = cmdLineParser.getCmdCommandLine().getOptionValue("file");
            if (fileName == null)
                throw  new UnsupportedOperationException("File path has not been specified");

            BufferedReader reader = getFileReader(fileName);
            if (reader == null)
                throw new UnsupportedOperationException ("File has not a valid path or the name it s wrong");

            for  (Option arg: cmdLineParser.getCmdCommandLine().getOptions()){
                if (mldMenuAlorithm.getAlgorithms().containsKey(arg.getValue())){
                 // Direct cast for now, but should retrieve the type via reflection o check instance type
                 calculationsResults.add((MLDResult)mldMenuAlorithm.getAlgorithms().get(arg.getValue()).calculate(reader));
                }
            }

        } catch (ParseException e) {
            logger.error("Something happen : " + e.getMessage());
            e.printStackTrace();
        }

    return  null; // no needed at this level .
    }

    /**
     * Give access to the file
     *
     * @param file
     * @return
     */
    private BufferedReader getFileReader(String file){
        try {
           return Files.newBufferedReader(Paths.get(file));
        } catch (IOException e) {
            logger.error("The current file / " + file + " / it s not available or the path it s wrong!!");
        }
        return null;
    }

    private Object[] getAlgorithmToExecute(){
       return context.getBeanNamesForAnnotation(MLDWireAlg.class);
    }


    @Override
    public void printResult() {
        for (MLDResult current : calculationsResults){
            logger.debug(current.toString());
        }
    }


}
