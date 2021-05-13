package io.jstate.core.services.io.jstate.core;

import java.util.Optional;

import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.exception.TransitionNotAllowedException;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.Transition;
import io.jstate.spi.JstateEnvironmentProvider;
import io.jstate.spi.JstateValidationService;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.State;

import static io.jstate.core.services.io.jstate.core.misc.JStateUtil.nullSafe;

public class DefaultJstateValidationService implements JstateValidationService {

    private ProcessTemplateRepository templateRepository;

    DefaultJstateValidationService(ProcessTemplateRepository templateRepository) {

        this.templateRepository = templateRepository;
    }

    @Override
    public void checkTransition(ProcessInstance processInstance, State from, State to) throws TransitionNotAllowedException {

        if (from == null || to == null || processInstance == null) {
            throw new TransitionNotAllowedException("Transition could not be checked due to invalid process information. Process template: '"
                    + nullSafe(processInstance, (p) -> p.getProcessTemplateId())
                    + "' from state '"
                    + from
                    + "' to state '"
                    + to
                    + "'");
        }

        ProcessTemplate processTemplate = this.templateRepository.getProcessTemplate(processInstance.getProcessTemplateId());
        Optional<Transition> first = processTemplate.getTransitions().stream().filter(s -> from.getId().equals(s.getFromStateId())).findFirst();

        if (first.isPresent() && !first.get().getToStateIds().contains(to.getId())) {
            throw new TransitionNotAllowedException(processInstance, from, to);
        } else if (!first.isPresent()) {
            throw new TransitionNotAllowedException("The given process template with id '"
                    + processInstance.getProcessTemplateId()
                    + "' does not define the state '"
                    + from.getId()
                    + "'", from, to);
        }

    }

    @Override
    public void checkTransition(ProcessTemplate processTemplate, State from, State to) throws TransitionNotAllowedException {

        if (from == null || to == null || processTemplate == null) {
            throw new TransitionNotAllowedException("Transition could not be checked due to invalid process information. Process template: '"
                    + nullSafe(processTemplate, (p) -> p.getId())
                    + "' from state '"
                    + from
                    + "' to state '"
                    + to
                    + "'");
        }

        Optional<Transition> first = processTemplate.getTransitions().stream().filter(s -> from.getId().equals(s.getFromStateId())).findFirst();

        if (first.isPresent() && !first.get().getToStateIds().contains(to.getId())) {
            throw new TransitionNotAllowedException(processTemplate, from, to);
        } else if (!first.isPresent()) {
            throw new TransitionNotAllowedException(
                    "The given process template with id '" + processTemplate.getId() + "' does not define the state '" + from.getId() + "'", from,
                    to);
        }
    }

}
