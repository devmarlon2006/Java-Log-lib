package io.github.devmarlon2006.SystemLog.log;

import io.github.devmarlon2006.SystemLog.log.models.SystemLog;
import io.github.devmarlon2006.SystemLog.system.exeptions.StepNotAvaliable;

import java.util.List;
import java.util.UUID;
import java.util.function.UnaryOperator;


public class LogHandle {

    private UUID LOG_ID;
    private int STATUS_CODE;
    private String MESSAGE;
    private List<Steps> LOG_STEPS;

    public LogHandle () {
    }

    public static LogHandle of() {
        return new LogHandle();
    }

    public LogHandle(UUID logId, String message, List<Steps> steps) {
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

    public LogHandle addMessage(String messageArgs) {
        this.MESSAGE = messageArgs;
        return (this);
    }

    public LogHandle addSteps(Steps addSteps){
        this.LOG_STEPS.add(addSteps);
        return (this);
    }

    /**
     * getters
     */

    public UUID getLogId() {
        return this.LOG_ID;
    }

    public String getMessage() {
        return this.MESSAGE;
    }

    public List<Steps> obtainLogSteps() {
        return this.LOG_STEPS;
    }

    public Steps getIndividualStep(int index) throws StepNotAvaliable {
        if (index > this.LOG_STEPS.size()) {
            throw new StepNotAvaliable("Step not avaliable: " + index);

        }
        return this.LOG_STEPS.get(index);
    }

    public int getStatusCode() {
        return this.STATUS_CODE;
    }
}