package io.jstate.spi;

public abstract class Processor {

    private ProcessRepository processRepository;

    private ProcessInstance processInstance;

    public abstract State process(ProcessInstance processInstance);

    public void logInfo(String message) {
        processRepository.logInfo(this.processInstance.getReservationId(), message);
    }

    public void logError(String message) {
        processRepository.logError(this.processInstance.getReservationId(), message);
    }

    public void logWarn(String message) {
        processRepository.logWarning(this.processInstance.getReservationId(), message);
    }

    public Processor setProcessRepository(ProcessRepository processRepository) {
        this.processRepository = processRepository;
        return this;
    }

    public Processor setProcessInstance(ProcessInstance processInstance) {
        this.processInstance = processInstance;
        return this;
    }
}
