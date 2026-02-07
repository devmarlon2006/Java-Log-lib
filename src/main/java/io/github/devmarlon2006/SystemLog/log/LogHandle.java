package io.github.devmarlon2006.SystemLog.log;

import io.github.devmarlon2006.SystemLog.log.models.Steps;
import io.github.devmarlon2006.SystemLog.log.models.SystemLog;
import io.github.devmarlon2006.SystemLog.system.exeptions.StepNotAvaliable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.UnaryOperator;


public class LogHandle extends Handle implements Buildable<SystemLog , LogHandle> {

    private UUID LOG_ID;
    private int SUPER_STATUS_CODE;
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


    /**
     * Builder
     */

    @Override
    public SystemLog build() {
        return new SystemLog(this);
    }

    @Override
    public LogHandle configure(UnaryOperator<LogHandle> config ) {
        return config.apply(this);
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
        this.SUPER_STATUS_CODE = status;
        return (this);
    }

    public LogHandle addMessage(String messageArgs) {
        if (getMessage() != null) {
            this.MESSAGE = messageArgs;
        }
        return (this);
    }

    public LogHandle addSteps(Steps addSteps){
        this.LOG_STEPS.add(addSteps);
        return (this);
    }

    public LogHandle addTrace() {
        if(this.isSuccess()) {
        }
        return backThis();
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
        if (this.LOG_STEPS == null) {
            this.LOG_STEPS = new ArrayList<>();
        }
        return this.LOG_STEPS;
    }

    //------

    public int getStatusCode() {
        return this.SUPER_STATUS_CODE;
    }

    public Steps getIndividualStep(int index) throws StepNotAvaliable {
        if (index > this.LOG_STEPS.size()) {
            throw new StepNotAvaliable("This step is not avaliable -> " + index);
        }
        return this.LOG_STEPS.get(index);
    }

    public List<Steps> filterSteps() {
        this.LOG_STEPS.removeIf(Objects::isNull);
        return null;
    }

    public boolean hasSteps() {
        return !this.LOG_STEPS.isEmpty();
    }

    public boolean isError() {
        return this.SUPER_STATUS_CODE >= 400;
    }

    public boolean isSuccess() {
        return !this.isError();
    }

}