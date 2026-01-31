package io.github.devmarlon2006.SystemLog;

import io.github.devmarlon2006.SystemLog.log.SystemLog;
import io.github.devmarlon2006.SystemLog.system.Logablee;

public class TestLog implements Logablee {

    private SystemLog log;
    private final String OperationType = "OPERATION TEST";

    public SystemLog createClass () {
        provinderLogInfo("teste");
        catchLogInfo();
        return null;
    }

    @Override
    public SystemLog provinderLogInfo(String message) {
        return this.log;
    }


    @Override
    public void catchLogInfo() {
        this.log = logInfo();
    }

    @Override
    public String provinderOperaationType() {
        return this.OperationType;
    }

    @Override
    public String provinderTrace() {
        return "" + this.getClass();
    }
}
