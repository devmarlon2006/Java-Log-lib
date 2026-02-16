package io.github.devmarlon2006.SystemLog.system;

import io.github.devmarlon2006.SystemLog.log.StepsHandle;

public interface Steppable {

    StepsHandle warn(String message , String trace);

    String provinderOperationType();


    default StepsHandle getSteps(String message , String trace) {
        return StepsHandle.of().configure( (S) -> S
                        .operation(provinderOperationType())
                        .message(message)
                        .trace(trace)
                );
    }
}