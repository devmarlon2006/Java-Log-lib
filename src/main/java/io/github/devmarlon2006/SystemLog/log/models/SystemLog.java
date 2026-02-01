package io.github.devmarlon2006.SystemLog.log.models;

import io.github.devmarlon2006.SystemLog.log.LogHandle;
import io.github.devmarlon2006.SystemLog.log.Steps;

import java.util.List;
import java.util.UUID;

public class SystemLog {
    private final UUID LOG_ID;
    private final String message;
    private final List<Steps> steps;

    public SystemLog(LogHandle ingectedLog) {
        this.LOG_ID = ingectedLog.getLogId();
        this.message = ingectedLog.getMessage();
        this.steps = ingectedLog.obtainLogSteps();
    }
}
