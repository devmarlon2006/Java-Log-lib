package io.github.devmarlon2006.SystemLog.log;

import io.github.devmarlon2006.SystemLog.log.models.Steps;
import io.github.devmarlon2006.SystemLog.system.Buildable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.function.UnaryOperator;

public class StepsHandle extends Handle implements Buildable<Steps , StepsHandle> {

    private String operationType;
    private String MESSAGE;
    private Timestamp OPERATION_TIME;
    private String stacTrace;

    @Override
    public Steps build() {
        return Steps.from(this);
    }

    @Override
    public StepsHandle configure(UnaryOperator<StepsHandle> configuration) {
        return configuration.apply(this);
    }

    public static StepsHandle of() {
        return new StepsHandle().timestamp();
    }

    /**
     * seters
     */

    public StepsHandle timestamp() {
        this.OPERATION_TIME = Timestamp.from(Instant.now());
        return (this);
    }

    public StepsHandle message(String message) {
        this.MESSAGE = message;
        return (this);
    }

    public StepsHandle trace(String trace) {
        this.stacTrace = trace;
        return (this);
    }

    public StepsHandle operation(String operation) {
        this.operationType = operation;
        return (this);
    }

    /**
     * getters
     */

    public String getMessage() {
        return MESSAGE;
    }

    public Timestamp getOperationTime() {
        return OPERATION_TIME;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getStackTrace() {
        return stacTrace;
    }


    public String getCamps(Camps camp ) {
        return switch (camp) {
            case MESSAGE -> getMessage();
            case OPERATION_TIME -> getOperationTime().toString();
            case OPERATION_TYPE -> getOperationType();
            case STACK_TRACE -> getStackTrace();
        };


    }

    public enum Camps {
        MESSAGE, OPERATION_TIME, OPERATION_TYPE, STACK_TRACE
    }

}