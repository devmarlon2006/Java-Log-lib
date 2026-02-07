package io.github.devmarlon2006.SystemLog.system;

import io.github.devmarlon2006.SystemLog.log.models.Steps;

public interface Steppable {

    Steps createSteps(String message);

    String provinderOperationType();


    default Steps getSteps(String message) {
        return null;
    }
}