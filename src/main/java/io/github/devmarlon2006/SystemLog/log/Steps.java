package io.github.devmarlon2006.SystemLog.log;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.function.UnaryOperator;

public class Steps {
    private String operationType;
    private Timestamp OPERATION_TIME;
    private String stacTrace;

    public static Steps of() {
        return new Steps();
    }

    public Steps configure(UnaryOperator<Steps> config) {
        return config.apply(this);
    }

    public void timestamp() {
        this.OPERATION_TIME = Timestamp.from(Instant.now());
    }

    public Steps trace(String trace) {
        this.stacTrace = trace;
        return this;
    }

    public Steps operation(String operation) {
        this.operationType = operation;
        return (this);
    }

}
