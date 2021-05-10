package io.jstate.core.services.io.jstate.core;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessInstanceFactory;

import java.time.LocalDateTime;
import java.util.Map;

public class InMemoryProcessInstanceFactory implements ProcessInstanceFactory {

    @Override
    public ProcessInstance create(ProcessTemplate template, Map<String, String> initialProperties) {

        if (template == null) {
            // TODO define exception
            throw new RuntimeException("Missing definition");
        }

        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setProcessTemplateId(template.getId());
        processInstance.setCreated(LocalDateTime.now());
        processInstance.setLastUpdate(LocalDateTime.now());
        processInstance.setProperties(initialProperties);

        return processInstance;
    }
}
