package io.jstate.core.services.io.jstate.core;

import io.jstate.core.services.io.jstate.core.exception.TransitionNotAllowedException;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.Transition;
import io.jstate.spi.JstateEnvironmentProvider;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.State;

import java.util.Optional;

public class JstateValidationService {

    private final JstateEnvironmentProvider env;

    JstateValidationService(JstateEnvironmentProvider environmentProvider) {

        this.env = environmentProvider;
    }

    void checkTransition(ProcessInstance processInstance, State from, State to) throws TransitionNotAllowedException {

        if (from == null || to == null || processInstance == null) {
            throw new TransitionNotAllowedException("Transition could not be checked due to invalid process information. Process template: '"
                    + processInstance.getProcessTemplateId()
                    + "' from state '"
                    + from
                    + "' to state '"
                    + to
                    + "'");
        }

        ProcessTemplate processTemplate = this.env.getProcessTemplateRepository().getProcessTemplate(processInstance.getProcessTemplateId());
        Optional<Transition> first = processTemplate.getTransitions().stream().filter(s -> from.getId().equals(s.getFromStateId())).findFirst();

        if (first.isPresent() && !first.get().getToStateIds().contains(to.getId())) {
            throw new TransitionNotAllowedException(processInstance, from, to);
        } else {
            throw new TransitionNotAllowedException("The given process template with id '"
                    + processInstance.getProcessTemplateId()
                    + "' does not define the state '"
                    + from.getId()
                    + "'", from, to);
        }

    }
}
