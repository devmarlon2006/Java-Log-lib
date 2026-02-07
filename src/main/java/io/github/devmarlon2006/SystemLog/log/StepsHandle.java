package io.github.devmarlon2006.SystemLog.log;

import io.github.devmarlon2006.SystemLog.log.models.Steps;

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
        return null;
    }

    @Override
    public StepsHandle configure(UnaryOperator<StepsHandle> configuration) {
        return configuration.apply(this);
    }

    private Exception casualExeption;

    public static StepsHandle of() {
        return new StepsHandle().timestamp();
    }

    public StepsHandle timestamp() {
        this.OPERATION_TIME = Timestamp.from(Instant.now());
        return (this);
    }

    public StepsHandle message(String message) {
        this.MESSAGE = message;
        return (this);
    }

    public StepsHandle trace() {
        this.stacTrace = casualExeption.getMessage();
        return (this);
    }

    public StepsHandle operation(String operation) {
        this.operationType = operation;
        return (this);
    }
}