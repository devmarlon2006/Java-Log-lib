package io.github.devmarlon2006.SystemLog.log.models;

import io.github.devmarlon2006.SystemLog.log.StepsHandle;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.function.UnaryOperator;

public class Steps {

    private String operationType;
    private String MESSAGE;
    private Timestamp OPERATION_TIME;
    private String stacTrace;

    private Exception casualExeption;

    public Steps(StepsHandle handle) {
        this.operationType = handle
    }

}
