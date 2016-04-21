package com.mdl.data.analyzer.cmd;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * The class hold the executable commands.
 * Created by angelo on 27/01/2016.
 */
public class MLDExcutableCommands {

    private Map<String, MLDCommand> executableCommands = new HashMap<>();

    public  void setExecutableCommand(String key, MLDCommand command ){
        executableCommands.put(key, command);
    }


    public Map<String,MLDCommand> getExecutableCommands(){
        return executableCommands;
    }


}
