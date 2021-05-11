package io.jstate.core.services.io.jstate.core;

import io.jstate.spi.exception.TransitionNotAllowedException;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.StateDefinition;
import io.jstate.model.configuration.Transition;
import io.jstate.spi.JstateEnvironmentProvider;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.ProcessorFactory;
import io.jstate.spi.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JstateValidationServiceTest {

    @Mock
    ProcessTemplateRepository templateRepository;

    @InjectMocks
    DefaultJstateValidationService subject = new DefaultJstateValidationService(templateRepository);

    @Test
    @DisplayName("Successful transition from defined state. NEW -> FINAL")
    void test_0001() {

        // @formatter:off
        ProcessTemplate processTemplate = new ProcessTemplate().setTransitions(
                asList(
                        transition("NEW", "FINAL")
                )
        );
        processTemplate.setStateDefinitions(
                asList(
                        new StateDefinition().setId("NEW"),
                        new StateDefinition().setId("FINAL")
                )
        );
        State state1 = new State().setId("NEW");
        State state2 = new State().setId("FINAL");
        
        ProcessInstance instance = new ProcessInstance();
        instance.getStates().add(state1);

        // @formatter:on
        when(templateRepository.getProcessTemplate(any())).thenReturn(processTemplate);

        try {
            this.subject.checkTransition(instance, state1, state2);
        } catch (Exception e) {
            e.printStackTrace();
            fail("No exception expected");
        }

    }

    @Test
    @DisplayName("Invalid transition from undefined state. UNKNOWN -> FINAL")
    void test_0010() {

        // @formatter:off
        ProcessTemplate processTemplate = new ProcessTemplate().setTransitions(
                asList(
                        transition("NEW", "FINAL")
                )
        );
        processTemplate.setStateDefinitions(
                asList(
                        new StateDefinition().setId("NEW"),
                        new StateDefinition().setId("FINAL")
                )
        );
        State state1 = new State().setId("UNKNOWN");
        State state2 = new State().setId("FINAL");

        ProcessInstance instance = new ProcessInstance();
        instance.getStates().add(state1);

        // @formatter:on
        when(templateRepository.getProcessTemplate(any())).thenReturn(processTemplate);

        try {
            this.subject.checkTransition(instance, state1, state2);
            fail("Expect Transition not alloed exception");
        } catch (TransitionNotAllowedException e) {
            e.getMessage().contains("does not define");
            assertEquals(state1, e.getStateFrom());
            assertEquals(state2, e.getStateTo());
        }

    }

    @Test
    @DisplayName("Invalid transition to undefined state. NEW -> UNKNOWN")
    void test_0020() {

        // @formatter:off
        ProcessTemplate processTemplate = new ProcessTemplate().setTransitions(
                asList(
                        transition("NEW", "FINAL")
                )
        );
        processTemplate.setStateDefinitions(
                asList(
                        new StateDefinition().setId("NEW"),
                        new StateDefinition().setId("FINAL")
                )
        );
        State state1 = new State().setId("NEW");
        State state2 = new State().setId("UNKNOWN");

        ProcessInstance instance = new ProcessInstance();
        instance.getStates().add(state1);

        // @formatter:on
        when(templateRepository.getProcessTemplate(any())).thenReturn(processTemplate);

        try {
            this.subject.checkTransition(instance, state1, state2);
            fail("Expect Transition not alloed exception");
        } catch (TransitionNotAllowedException e) {
            e.getMessage().contains("is not allowed");
            assertEquals(state1, e.getStateFrom());
            assertEquals(state2, e.getStateTo());
        }

    }

    private Transition transition(String from, String to) {

        return new Transition().setFromStateId(from).setToStateIds(asList(to));
    }

}
