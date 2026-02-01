package io.github.devmarlon2006.SystemLog.log;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.function.UnaryOperator;

public class Steps {
    private String operationType;
    private String MESSAGE;
    private Timestamp OPERATION_TIME;
    private String stacTrace;

    public static Steps of() {
        return new Steps().timestamp();
    }

    public Steps configure(UnaryOperator<Steps> config) {
        return config.apply(this);
    }

    public Steps timestamp() {
        this.OPERATION_TIME = Timestamp.from(Instant.now());
        return (this);
    }

    public Steps message(String message) {
        this.MESSAGE = message;
        return (this);
    }

    public Steps trace(String trace) {
        this.stacTrace = trace;
        return (this);
    }

    public Steps operation(String operation) {
        this.operationType = operation;
        return (this);
    }

}
