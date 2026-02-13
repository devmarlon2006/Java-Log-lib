package io.github.devmarlon2006.SystemLog.log;

import io.github.devmarlon2006.SystemLog.log.models.Steps;
import io.github.devmarlon2006.SystemLog.log.models.SystemLog;
import io.github.devmarlon2006.SystemLog.system.Buildable;
import io.github.devmarlon2006.SystemLog.system.exeptions.StepNotAvaliable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LogHandle extends Handle implements Buildable<SystemLog , LogHandle> {

    private UUID LOG_ID;
    private int SUPER_STATUS_CODE;
    private String MESSAGE;
    private List<Steps> LOG_STEPS = new ArrayList<>();
    private List<StepsHandle> stepsHandlesBuffer = new ArrayList<>();

    public LogHandle () {
    }

    public LogHandle(UUID logId, String message, List<Steps> steps) {
        LOG_ID = logId;
        this.MESSAGE = message;
        this.LOG_STEPS = steps;
    }

    public static LogHandle of() {
        return new LogHandle();
    }


    /**
     * Implementation of Buildable
     */

    @Override
    public SystemLog build() {
        return SystemLog.of(this);
    }

    @Override
    public LogHandle configure(UnaryOperator<LogHandle> config ) {
        return config.apply(this);
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

    public LogHandle addSteps(StepsHandle addSteps){
        this.stepsHandlesBuffer.add(addSteps);
        return (this);
    }

    public LogHandle addTrace() {
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

    public List<Steps> obtainLogSteps() throws NullPointerException {
        if (this.LOG_STEPS.isEmpty()) {
            throw new NullPointerException("Steps not avaliable");
        }
        return this.LOG_STEPS;
    }

    //------

    public int getStatusCode() {
        return this.SUPER_STATUS_CODE;
    }

    public LogHandle buildSteps() {
        if (this.stepsHandlesBuffer != null) {
            for (StepsHandle step : this.stepsHandlesBuffer) {
                assert false;
                this.LOG_STEPS.add(step.build());
            }
        }
        return this;
    }

    public List<StepsHandle> filterStepsAndSet(String filter , StepsHandle.Camps camp) {
        Predicate<StepsHandle> condition = (step) -> step.getCamps(camp).contains(filter);
        this.stepsHandlesBuffer = this.stepsHandlesBuffer.stream().filter(condition).toList();
        return this.stepsHandlesBuffer;
    }

    public List<StepsHandle> filterStepsAndGet(String filter , StepsHandle.Camps camp) {
        Predicate<StepsHandle> condition = (step) -> step.getCamps(camp).contains(filter);
        return this.stepsHandlesBuffer.stream().filter(condition).toList();
    }

    public StepsHandle getIndividualStep(int index) throws StepNotAvaliable {
        if (index > this.stepsHandlesBuffer.size()) {
            throw new StepNotAvaliable("This step is not avaliable -> " + index);
        }
        return this.stepsHandlesBuffer.get(index);
    }

    public boolean hasSteps() {
        return !this.LOG_STEPS.isEmpty();
    }

    public boolean hasStepsBuffer() {
        return !this.stepsHandlesBuffer.isEmpty();
    }

    public boolean isError() {
        return this.SUPER_STATUS_CODE >= 400;
    }

    public boolean isSuccess() {
        return !this.isError();
    }

}