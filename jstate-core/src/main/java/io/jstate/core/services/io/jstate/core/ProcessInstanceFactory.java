package io.jstate.core.services.io.jstate.core;

import io.jstate.model.configuration.ProcessDefinition;
import io.jstate.spi.ProcessInstance;

import java.time.LocalDateTime;
import java.util.Map;

public class ProcessInstanceFactory {

    public ProcessInstance newProcessInstance(ProcessDefinition definition, Map<String, String> initialProperties) {

        if (definition == null) {
            // TODO define exception
            throw new RuntimeException("Missing definition");
        }

        ProcessInstance processInstance = new ProcessInstance();
        processInstance.setProcessDefinitionId(definition.getId());
        processInstance.setCreated(LocalDateTime.now());
        processInstance.setLastUpdate(LocalDateTime.now());
        processInstance.setProperties(initialProperties);

        return processInstance;

    }
}
