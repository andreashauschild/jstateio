package io.jstate.core.services.io.jstate.core;

import static io.jstate.spi.DefaultStates.CLOSED;
import static io.jstate.spi.DefaultStates.FINAL;
import static io.jstate.spi.DefaultStates.PAUSED;
import static java.util.Arrays.asList;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.ProcessorDefinition;
import io.jstate.spi.JProcessService;
import io.jstate.spi.JProcessUtilService;
import io.jstate.spi.ProcessExecutor;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.Processor;
import io.jstate.spi.ProcessorFactory;
import io.jstate.spi.State;
import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.TransitionNotAllowedException;
import io.jstate.spi.exception.UnknownProcessorException;

public class DefaultProcessExecutor implements ProcessExecutor {

    static final Logger logger = Logger.getLogger(DefaultProcessExecutor.class.getName());
    private ProcessTemplateRepository tmplRepo;

    private Set<String> stopExecutionState = new HashSet<>(asList(CLOSED, FINAL, PAUSED));

    private JProcessService processService;

    private ProcessRepository processRepository;

    private ProcessorFactory processorFactory;

    private JProcessUtilService processUtilService;

    @Override
    public void execute(String processInstanceId) {

        State currentState;
        State newState = null;
        ProcessInstance reserved = null;
        try {
            reserved = this.processService.reserve(processInstanceId);
            do {
                currentState = reserved.getCurrentState();
                newState = runProcessors(reserved);
                if (newState != null) {
                    reserved = this.processService.transition(reserved.getReservationId(), newState);
                }
            } while (newStateAllowsFurtherProcessing(newState, currentState));

        } catch (AlreadyReservedException e) {
            logger.info("Execution of process with id '" + processInstanceId + "' is skipped. Process has already a reservation.");
        } catch (TransitionNotAllowedException e) {
            this.processUtilService.toError(reserved.getReservationId(), e, newState.getProperties());
        } catch (UnknownProcessorException e) {
            this.processUtilService.toError(reserved.getReservationId(), e, new HashMap<>());
        } finally {
            if (reserved != null) {
                this.processService.cancel(reserved.getReservationId());
            }
        }
    }

    private State runProcessors(ProcessInstance reserved) {

        State result = null;
        ProcessTemplate processTemplate = this.tmplRepo.getProcessTemplate(reserved.getProcessTemplateId());

        for (ProcessorDefinition processor : processTemplate.getProcessors()) {
            LocalDateTime start = LocalDateTime.now();
            Optional<Processor> o = this.processorFactory.create(processor);

            if (o.isPresent()) {
                o.get().setProcessInstance(reserved);
                o.get().setProcessRepository(processRepository);
                LocalDateTime end = LocalDateTime.now();
                State newState = o.get().process(reserved);

                // The first state transition will be used as result, but all processors will be executed
                if (result == null && newState != null) {
                    newState.setBegin(start);
                    newState.setEnd(end);
                    result = newState;
                }
            } else {
                throw new UnknownProcessorException(processor.getClazz());
            }
        }

        return result;
    }

    private boolean newStateAllowsFurtherProcessing(State newState, State oldState) {

        if (newState == null) {
            return false;
        }

        if (stopExecutionState.contains(newState.getName())) {
            return false;
        }
        System.out.println(oldState);
        System.out.println(newState);
        return !oldState.getName().equalsIgnoreCase(newState.getName());
    }

    /**
     * Gets the value of the tmplRepo property.
     *
     * @return possible object is {@link ProcessTemplateRepository}
     */
    public ProcessTemplateRepository getTmplRepo() {

        return tmplRepo;
    }

    /**
     * Gets the value of the processService property.
     *
     * @return possible object is {@link JProcessService}
     */
    public JProcessService getProcessService() {

        return processService;
    }

    /**
     * Sets the value of the processService property
     *
     * @param processService allowed object is {@link JProcessService }
     * @return the {@link DefaultProcessExecutor}
     */
    public DefaultProcessExecutor setProcessService(JProcessService processService) {

        this.processService = processService;
        return this;
    }

    /**
     * Gets the value of the processorFactory property.
     *
     * @return possible object is {@link ProcessorFactory}
     */
    public ProcessorFactory getProcessorFactory() {

        return processorFactory;
    }

    /**
     * Sets the value of the processorFactory property
     *
     * @param processorFactory allowed object is {@link ProcessorFactory }
     * @return the {@link DefaultProcessExecutor}
     */
    public DefaultProcessExecutor setProcessorFactory(ProcessorFactory processorFactory) {

        this.processorFactory = processorFactory;
        return this;
    }

    /**
     * Sets the value of the tmplRepo property
     *
     * @param tmplRepo allowed object is {@link ProcessTemplateRepository }
     * @return the {@link DefaultProcessExecutor}
     */
    public DefaultProcessExecutor setTmplRepo(ProcessTemplateRepository tmplRepo) {

        this.tmplRepo = tmplRepo;
        return this;
    }

    public DefaultProcessExecutor setProcessUtilService(JProcessUtilService processUtilService) {
        this.processUtilService = processUtilService;
        return this;
    }

    public DefaultProcessExecutor setProcessRepository(ProcessRepository processRepository) {
        this.processRepository = processRepository;
        return this;
    }
}
