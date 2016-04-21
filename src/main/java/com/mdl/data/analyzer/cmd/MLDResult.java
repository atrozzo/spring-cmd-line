package com.mdl.data.analyzer.cmd;

import java.time.LocalDate;
import java.util.Map;

/**
 * Created by angelo on 26/01/2016.
 */
public interface MLDResult {

    public Integer getStatusCode();

    public String getErrorMsg();

    public Map<String, String> getDataResult();


}
