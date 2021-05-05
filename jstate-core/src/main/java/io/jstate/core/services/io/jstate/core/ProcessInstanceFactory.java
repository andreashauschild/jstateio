package io.jstate.core.services.io.jstate.core;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;

import java.time.LocalDateTime;
import java.util.Map;

public class ProcessInstanceFactory {

    public ProcessInstance newProcessInstance(ProcessTemplate template, Map<String, String> initialProperties) {

        if (template == null) {
            // TODO define exception
            throw new RuntimeException("Missing definition");
        }

        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setProcessDefinitionId(template.getId());
        processInstance.setCreated(LocalDateTime.now());
        processInstance.setLastUpdate(LocalDateTime.now());
        processInstance.setProperties(initialProperties);

        return processInstance;

    }
}
