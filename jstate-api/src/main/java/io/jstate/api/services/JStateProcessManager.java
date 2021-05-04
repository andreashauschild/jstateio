package io.jstate.api.services;

/**
 * 
 *
 * @author Andreas Hauschild *
 */
public interface JStateProcessManager {

    /**
     * Reserves a process by it's id
     * 
     * @param id
     *            id of process to reserve
     * @return reserved process
     */
    Process reserve(String id);
}
