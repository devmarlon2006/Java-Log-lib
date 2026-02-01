package io.github.devmarlon2006.SystemLog.system;

import io.github.devmarlon2006.SystemLog.log.Steps;

public interface Steppable {

    Steps createSteps(String message);

    String provinderOperationType();


    default Steps getSteps(String message) {
        return Steps.of().configure( (config) -> config
                .operation(this.provinderOperationType())
                .trace(message)
        );
    }

}