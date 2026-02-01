package io.github.devmarlon2006.SystemLog;

import io.github.devmarlon2006.SystemLog.log.SystemLog;
import io.github.devmarlon2006.SystemLog.log.Steps;

import java.util.UUID;
import java.util.function.UnaryOperator;


public class LogHandle {

    private UUID LOG_ID;
    private int STATUS_CODE;
    private String[] MESSAGE;
    private Steps LOG_STEPS;

    public LogHandle () {
    }

    public static LogHandle of() {
        return new LogHandle();
    }

    public LogHandle(UUID logId, String[] message, Steps steps) {
        LOG_ID = logId;
        this.MESSAGE = message;
        this.LOG_STEPS = steps;
    }

    public SystemLog build() {
        return new SystemLog(this);
    }

    public LogHandle configure(UnaryOperator<LogHandle> logConfig ) {
        return logConfig.apply(this);
    }

    private LogHandle backThis () {
        return (this);
    }

    /**
     * seters
     */

    public LogHandle addId() {
        this.LOG_ID = UUID.randomUUID();
        return (this);
    }

    public LogHandle statusCode(int status) {
        this.STATUS_CODE = status;
        return (this);
    }

    public LogHandle addMessage(String... messageArgs) {
        this.MESSAGE = messageArgs;
        return (this);
    }

    public LogHandle addSteps(Steps addSteps){
        this.LOG_STEPS = addSteps;
        return (this);
    }

    /**
     * getters
     */

    public UUID getLogId() {
        return this.LOG_ID;
    }

    public String getOneMessage(int index) {
        return this.MESSAGE[index];
    }

    public String[] getAllMessages () {
        return this.MESSAGE;
    }

    public Steps getSteps() {
        return this.LOG_STEPS;
    }

}