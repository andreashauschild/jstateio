package io.jstate.spi.exception;

import io.jstate.spi.ProcessInstance;

public class AlreadyReservedException extends RuntimeException {

    public AlreadyReservedException(ProcessInstance processInstance) {

        super("The process instance with id "
                + processInstance.getId()
                + " was already reserved. Cancel the reservation to make it available again.");
    }
}
