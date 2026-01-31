package io.github.devmarlon2006.SystemLog.log;

import io.github.devmarlon2006.SystemLog.LogHandle;

import java.util.UUID;

public class SystemLog {
    private final UUID LOG_ID;
    private final String[] message;
    private final Steps steps;

    public SystemLog(LogHandle ingectedLog) {
        this.LOG_ID = ingectedLog.getLogId();
        this.message = ingectedLog.getAllMessages();
        this.steps = ingectedLog.getSteps();
    }
}
