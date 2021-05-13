package io.jstate.spi.exception;

public class ProcessInstanceReservationNotExistsException extends RuntimeException {

    public ProcessInstanceReservationNotExistsException(String reservationId) {

        super("The process instance with reservation id " + reservationId + " does not exists.");
    }
}
