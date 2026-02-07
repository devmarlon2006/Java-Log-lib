package io.github.devmarlon2006.SystemLog.system;

import io.github.devmarlon2006.SystemLog.log.LogHandle;
import io.github.devmarlon2006.SystemLog.log.models.SystemLog;

public interface Logablee {

    /**
     * Methods para o log de sistema;
     */

    SystemLog provinderLogInfo(String message);


    /**
     *
     * public void catchLogInfo() {
     *     this.youLog = logInfo();
     * }
     */
    void catchLogInfo();


    default  LogHandle logInfo(String message) {
        return LogHandle.of().configure((logParams) -> logParams
                .addId()
                .statusCode(200)
                .addMessage(message)
                .addSteps(null)
        );
    }

    default LogHandle logError(String message) {
        SystemLog cretedLogError = new LogHandle().configure( (logParams) -> logParams ).build();
        return null;
    }
}