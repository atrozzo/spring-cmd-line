package com.mdl.data.analyzer.conf;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import java.util.ListIterator;

/**
 * This class has to pars  the command line arguments. The PosixParser help to fix an issue with the unrecognised commans
 * in the common-cli lib.
 */
public class MLDCmdLineParser extends PosixParser {

    private CommandLine cmdCommandLine;
    private MLDMenuOptions options;
    private String[] cmdArgs;
    private boolean isMandatory;

    public void setMLDCmdLineParser(MLDMenuOptions opts, String[] args, boolean mandatory){
        options = opts;
        cmdArgs = args;
        isMandatory = mandatory;
    }

    public MLDCmdLineParser() {

    }

    /**
     * The methos give access to the command line with all the arguments that have been digit
     *
     * @return
     * @throws ParseException
     */
    public CommandLine getCmdCommandLine() throws ParseException {
        cmdCommandLine = parse(options,cmdArgs,isMandatory);
        return  cmdCommandLine;
    }


    @Override
    protected void processOption(String arg, ListIterator iter) throws ParseException
    {
        try {
            super.processOption(arg, iter);
        } catch (ParseException e) {
            // e.printStackTrace();
        }
    }

}
