package com.mdl.data.analyzer.cmd.menu;

import com.mdl.data.analyzer.MLDStatusCodes;
import com.mdl.data.analyzer.cmd.MLDCommand;
import com.mdl.data.analyzer.cmd.MLDCommandLineResult;
import com.mdl.data.analyzer.cmd.MLDResult;
import com.mdl.data.analyzer.conf.MLDWireOption;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by angelo on 29/01/2016.
 */


@MLDWireOption(value = "file", description = ("hold the file location informations"),
        shortValue = ("f"))
@Service(value = "file")
public class MLDMenuFile implements MLDCommand<MLDResult> {

    @Override
    public MLDResult executeCmd() {
        return   new MLDCommandLineResult.CmdResultBuilder(MLDStatusCodes.INFO.getCode(), "File command has been executed").
                time(LocalDate.now()).dataResult(null).build();
    }

    @Override
    public void printResult() {
        // do nothing
    }

}
