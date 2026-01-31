package io.github.devmarlon2006.SystemLog.system;

import io.github.devmarlon2006.SystemLog.LogHandle;
import io.github.devmarlon2006.SystemLog.log.Steps;
import io.github.devmarlon2006.SystemLog.log.SystemLog;

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


    default  SystemLog logInfo() {
        SystemLog createdLogInfo = LogHandle.of().configure((logParams) -> logParams
                .addId()
                .statusCode(200)
                .addMessage("")
                .addSteps(null)
        ).build();
        return createdLogInfo;
    }

    default SystemLog logError() {
        SystemLog cretedLogError = new LogHandle().configure( (logParams) -> logParams ).build();
        return null;
    }

    default Steps createSteps() {
        Steps systemSteps = Steps.of().configure(conf -> conf.trace("nada"));
        return null;
    }

    /**
     * Metodos para os passos do log
     */

    String provinderOperaationType();

    String provinderTrace();

}