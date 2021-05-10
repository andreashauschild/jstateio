package io.jstate.spi;

public interface DefaultStates {

    /**
     * If a process is created it will always start in the state NEW
     */
    String NEW = "NEW";

    /**
     * This state is set to a process if the execution of a processor fails with an exception
     */
    String ERROR = "ERROR";

    /**
     * This state must be used of the process is complete in a defined way
     */
    String FINAL = "FINAL";

    /**
     * This state must be used if the process should be stopped and never reprocessed again. This can be used if a process becomes obsolete.
     */
    String CLOSED = "CLOSED";

}
