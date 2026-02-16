package io.github.devmarlon2006.SystemLog.log;

import io.github.devmarlon2006.SystemLog.log.models.Steps;
import io.github.devmarlon2006.SystemLog.log.models.SystemLog;
import io.github.devmarlon2006.SystemLog.system.Buildable;
import io.github.devmarlon2006.SystemLog.system.exeptions.StepNotAvaliable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LogHandle extends Handle implements Buildable<SystemLog , LogHandle> {

    private UUID LOG_ID;                                          // (gerado automaticamente) exigido
    private int SUPER_STATUS_CODE;                                // (use a interface 'Logable' ou defina você mesmo) exigido
    private String MESSAGE;                                       // opcional mas recomendado
    private final List<Steps> LOG_STEPS = new ArrayList<>();      // exigido pois <Steps> é uma classe imutavel
    private List<StepsHandle> STEPS_HANDLES = new ArrayList<>();  // exixido para criar os steps apartir de <StepsHandle>

    public LogHandle () {
    }

    public LogHandle(UUID logId, String message) {
        LOG_ID = logId;
        this.MESSAGE = message;
    }

    public static LogHandle of() {
        return new LogHandle();
    }

    public boolean isError() {
        return this.SUPER_STATUS_CODE >= 400;
    }

    public boolean isSuccess() {
        return !this.isError();
    }

    /**
     * Implementation of Buildable
     */

    @Override
    public SystemLog build() {
        return SystemLog.from(this);
    }

    @Override
    public LogHandle configure(UnaryOperator<LogHandle> config ) {
        return config.apply(this);
    }

    /**
     * seters
     */

    public LogHandle addId() {
        this.LOG_ID = UUID.randomUUID();
        return (this);
    }

    public LogHandle statusCode(int status) {
        this.SUPER_STATUS_CODE = status;
        return (this);
    }

    public LogHandle addMessage(String messageArgs) {
        if (getMessage() != null) {
            this.MESSAGE = messageArgs;
        }
        return (this);
    }

    public LogHandle addStepHandle(StepsHandle addSteps){
        this.STEPS_HANDLES.add(addSteps);
        return (this);
    }

    /**
     * getters
     */

    public UUID getLogId() {
        return this.LOG_ID;
    }

    public String getMessage() {
        return this.MESSAGE;
    }

    public int getStatusCode() {
        return this.SUPER_STATUS_CODE;
    }

    public Optional<List<Steps>> obtainLogSteps() throws NullPointerException {
        return this.LOG_STEPS.isEmpty() ? Optional.empty() : Optional.of(this.LOG_STEPS);
    }

    public Optional<List<StepsHandle>> obtainLogStepsHandle() {
        return Optional.of(this.STEPS_HANDLES);
    }

    /**
     * StepsHandle collection manipulation methods
     */

    public List<StepsHandle> filterStepsHandleAndGet(String filter , StepsHandle.Camps camp) {
        Predicate<StepsHandle> condition = (step) -> step.getCamps(camp).contains(filter);
        return this.STEPS_HANDLES.stream().filter(condition).toList();
    }

    public List<StepsHandle> filterStepsHandleAndSet(String filter , StepsHandle.Camps camp) {
        this.STEPS_HANDLES = this.filterStepsHandleAndGet(filter, camp);
        return this.STEPS_HANDLES;
    }

    public StepsHandle getIndividualStepHandle(int index) throws StepNotAvaliable {
        if (index > this.STEPS_HANDLES.size() || this.hasStepsHandle()) {
            throw new StepNotAvaliable("This step is not avaliable -> " + index);
        }
        return this.STEPS_HANDLES.get(index);
    }

    public boolean hasStepsHandle() {
        return !this.STEPS_HANDLES.isEmpty();
    }


    /**
     * Steps collection manipulation methods
     */

    public LogHandle buildSteps() throws StepNotAvaliable {
        if (this.hasStepsHandle()) {
            for (StepsHandle step : this.STEPS_HANDLES) {
                this.LOG_STEPS.add(step.build());
            }
        }else {
            throw new StepNotAvaliable("Não existe passos para serem criados");
        }
        return this;
    }

    public void transformStepsAndBuild(List<StepsHandle> handleSteps) {
        this.STEPS_HANDLES = handleSteps;
        this.configure((S) -> {
            try {
                return S.buildSteps();
            } catch (StepNotAvaliable e) {
                throw new RuntimeException(e);
            }
        });
    }

    public boolean hasSteps() {
        return !this.LOG_STEPS.isEmpty();
    }
}