package io.github.devmarlon2006.SystemLog.system;

import io.github.devmarlon2006.SystemLog.log.LogHandle;

/**
 * Use esta interface para padronizar a criação dos logs,
 * ou crie você mesmo, mas eu recomendo para deixar o codigo mais limpo
 * e facil de entender.
 */

public interface Logablee {


    /**
     * Methods para o log de sistema;
     */

    LogHandle provinderLogInfo(String message);

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