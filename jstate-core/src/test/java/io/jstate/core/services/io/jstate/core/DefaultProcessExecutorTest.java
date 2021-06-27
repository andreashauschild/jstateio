package io.jstate.core.services.io.jstate.core;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.jstate.core.services.io.jstate.core.state.FinalState;
import io.jstate.core.services.io.jstate.core.state.NewState;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.ProcessorDefinition;
import io.jstate.model.configuration.Transition;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.Processor;
import io.jstate.spi.ProcessorFactory;
import io.jstate.spi.State;

@ExtendWith(MockitoExtension.class)
class DefaultProcessExecutorTest {

    @Mock
    ProcessorFactory processorFactory;

    @Mock
    ProcessTemplateRepository templateRepository;

    @Mock
    DefaultJProcessService jstateService;

    @InjectMocks
    DefaultProcessExecutor subject;

    @Captor
    ArgumentCaptor<State> stateCaptor;

    @Test
    @DisplayName("Execute the process from NEW->STATE1->STATE2->FINAL in a single execution.")
    void test_0001() throws Exception {

        ProcessTemplate processTemplate = new ProcessTemplate().setProcessors(asList(new ProcessorDefinition()));

        State newState = new NewState(new HashMap<>());
        State state1 = new State().setId("STATE1");
        State state2 = new State().setId("STATE2");
        State finalState = new FinalState(new HashMap<>());
        ProcessInstance instance = new ProcessInstance();
        instance.getStates().add(newState);

        when(this.templateRepository.getProcessTemplate(any())).thenReturn(processTemplate);
        when(this.jstateService.reserve(any())).thenReturn(instance);

        when(this.jstateService.transition(any(), any())).thenAnswer((param) -> {
            instance.getStates().add(param.getArgument(1));
            return instance;
        });

        when(this.processorFactory.create(any(), any())).thenReturn(java.util.Optional.of((Processor) processInstance -> {
            System.out.println(processInstance);
            if (processInstance.getCurrentState().getId().equals(newState.getId())) {
                return state1;
            }
            if (processInstance.getCurrentState().getId().equals(state1.getId())) {
                return state2;
            }
            if (processInstance.getCurrentState().getId().equals(state2.getId())) {
                return finalState;
            }
            Assertions.fail("Should never be reached. Processing ends with FINAL State");
            return null;
        }));

        this.subject.execute("id");

        verify(this.jstateService, times(3)).transition(any(), stateCaptor.capture());

        assertEquals(state1, stateCaptor.getAllValues().get(0));
        assertEquals(state2, stateCaptor.getAllValues().get(1));
        assertEquals(finalState, stateCaptor.getAllValues().get(2));

    }

    private Transition transition(String from, String to) {

        return new Transition().setFromState(from).setToState(asList(to));
    }

}
