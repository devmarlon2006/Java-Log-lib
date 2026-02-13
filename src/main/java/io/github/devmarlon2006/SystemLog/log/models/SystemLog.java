package io.github.devmarlon2006.SystemLog.log.models;

import io.github.devmarlon2006.SystemLog.log.LogHandle;

import java.util.List;
import java.util.UUID;

public class SystemLog {
    private final UUID LOG_ID;
    private final int statusCode;
    private final String message;
    private final List<Steps> steps;

    private SystemLog(LogHandle handle) {
        this.LOG_ID = handle.getLogId();
        this.statusCode = handle.getStatusCode();
        this.message = handle.getMessage();
        this.steps = handle.obtainLogSteps();
    }

    public static SystemLog of(LogHandle ingectedLog) {
        return new SystemLog(ingectedLog);
    }
}
