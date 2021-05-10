package io.jstate.core.services.io.jstate.core;

import static io.jstate.spi.DefaultStates.FINAL;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.jstate.core.services.io.jstate.core.state.FinalState;
import io.jstate.core.services.io.jstate.core.state.NewState;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.Transition;
import io.jstate.spi.JstateEnvironmentProvider;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.Processor;
import io.jstate.spi.ProcessorFactory;
import io.jstate.spi.State;

@ExtendWith(MockitoExtension.class)
class ProcessExecutorTest {

    @Mock
    ProcessRepository processRepository;

    @Mock
    ProcessTemplateRepository templateRepository;

    @Mock
    ProcessorFactory processorFactory;

    @Mock
    JstateValidationService validationService;

    @InjectMocks
    ProcessExecutor subject = new ProcessExecutor(mockProvider());

    @Test
    @DisplayName("Execute the process from NEW->STATE1->STATE2->FINAL in a single execution.")
    void test_0001() throws IOException, InterruptedException {

        // @formatter:off
        ProcessTemplate processTemplate = new ProcessTemplate().setTransitions(
                asList(
                        transition("NEW", "STATE1"),
                        transition("STATE1", "STATE2"),
                        transition("STATE2", FINAL)
                )
        );
        // @formatter:on

        State newState = new NewState(new HashMap<>());
        State state1 = new State().setId("STATE1");
        State state2 = new State().setId("STATE2");
        State finalState = new FinalState(new HashMap<>());
        ProcessInstance instance = new ProcessInstance();
        instance.getStates().add(newState);

        when(this.processRepository.reserveProcessInstance(any())).thenReturn(instance);

        when(this.processRepository.updateProcessInstance(any(), any())).thenAnswer((param) -> {
            instance.getStates().add(param.getArgument(1));
            return instance;
        });

        when(this.templateRepository.getProcessTemplate(any())).thenReturn(processTemplate);

        when(this.processorFactory.create(any(), any())).thenReturn((Processor) processInstance -> {

            if (processInstance.getCurrentState().getId().equals(newState.getId())) {
                return state1;
            }
            if (processInstance.getCurrentState().getId().equals(state1.getId())) {
                return state2;
            }
            if (processInstance.getCurrentState().getId().equals(state2.getId())) {
                return finalState;
            }
            Assertions.fail("Should never be reached");
            return null;
        });

        this.subject.execute("id");
    }

    private Transition transition(String from, String to) {

        return new Transition().setFromStateId(from).setToStateIds(asList(to));
    }

    private JstateEnvironmentProvider mockProvider() {

        return new JstateEnvironmentProvider() {

            @Override
            public ProcessRepository getProcessRepository() {

                return processRepository;
            }

            @Override
            public ProcessTemplateRepository getProcessTemplateRepository() {

                return templateRepository;
            }

            @Override
            public ProcessInstanceFactory getProcessInstanceFactory() {

                return null;
            }

            @Override
            public ProcessorFactory getProcessorFactory() {

                return processorFactory;
            }

            @Override
            public ExecutorService getExecutorService() {

                return null;
            }
        };

    }
}
