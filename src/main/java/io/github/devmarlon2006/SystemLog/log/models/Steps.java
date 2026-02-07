package io.github.devmarlon2006.SystemLog.log.models;

import io.github.devmarlon2006.SystemLog.log.StepsHandle;

import java.sql.Timestamp;


public class Steps {

    private final String operationType;
    private final  String MESSAGE;
    private final Timestamp OPERATION_TIME;
    private final String stacTrace;

    private Exception casualExeption;

    public Steps(StepsHandle handle) {
        this.operationType = handle.getOperationType();
        this.MESSAGE = handle.getMessage();
        this.OPERATION_TIME = handle.getOperationTime();
        this.stacTrace = handle.getStackTrace();
    }

}
