package io.github.devmarlon2006.SystemLog.system;

import io.github.devmarlon2006.SystemLog.log.LogHandle;
import io.github.devmarlon2006.SystemLog.log.models.SystemLog;

public interface Logablee {


    /**
     * Methods para o log de sistema;
     */

    SystemLog provinderLogInfo(String message);

    default LogHandle initializeLogInfo(String message) {
        return LogHandle.of().configure((logParams) -> logParams
                .addId()
                .statusCode(200)
                .addMessage(message)
        );
    }

    LogHandle provinderLogError(String message);

    default LogHandle initializeLogError(String message) {
        return LogHandle.of().configure( (logParams) -> logParams
                .addId()
                .statusCode(500)
                .addMessage(message)
        );
    }
}