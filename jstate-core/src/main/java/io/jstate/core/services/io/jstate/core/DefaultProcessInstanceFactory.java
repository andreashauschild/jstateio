package io.jstate.core.services.io.jstate.core;

import io.jstate.core.services.io.jstate.core.state.NewState;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessInstanceFactory;

import java.time.LocalDateTime;
import java.util.Map;

public class DefaultProcessInstanceFactory implements ProcessInstanceFactory {

    @Override
    public ProcessInstance create(ProcessTemplate template, Map<String, String> initialProperties) {

        if (template == null) {
            // TODO define exception
            throw new IllegalArgumentException("Missing definition");
        }

        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setProcessTemplateId(template.getId());
        processInstance.setCreated(LocalDateTime.now());
        processInstance.setLastUpdate(LocalDateTime.now());
        processInstance.getStates().add(new NewState(initialProperties).setBegin(LocalDateTime.now()).setEnd(LocalDateTime.now()));

        return processInstance;
    }
}
