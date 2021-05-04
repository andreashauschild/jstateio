package io.jstate.core.services.io.jstate.core;

import io.jstate.model.configuration.ProcessDefinition;
import io.jstate.spi.ProcessDefinitionQuery;
import io.jstate.spi.ProcessDefinitionRepository;
import io.jstate.spi.ProcessInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static io.jstate.core.services.io.jstate.core.ObjectUtil.cloneObject;

public class InMemoryProcessDefinitionRepository implements ProcessDefinitionRepository {

    private Map<String, ProcessDefinition> instances = new HashMap<>();

    @Override
    public ProcessDefinition updateProcessDefinition(String id, ProcessDefinition definition) {

        if (id == null) {
            throw new RuntimeException("Error: Missing id");
        }

        if (definition == null) {
            throw new RuntimeException("Error: Missing definition");
        }

        if (instances.containsKey(id)) {
            this.instances.put(id, definition);
            return cloneObject(this.instances.get(id));
        }
        // TODO define exception
        throw new RuntimeException("ProcessDefinition with id " + id + " does not exists");

    }

    @Override
    public ProcessDefinition getProcessDefinition(String id) {

        return cloneObject(this.instances.get(id));
    }

    @Override
    public ProcessDefinition createProcessDefinition(ProcessDefinition processDefinition) {

        String uuid = UUID.randomUUID().toString();
        this.instances.put(uuid, cloneObject(processDefinition));
        return getProcessDefinition(uuid);
    }

    @Override
    public List<ProcessDefinition> findProcessDefinition(ProcessDefinitionQuery query) {

        return null;
    }

    @Override
    public boolean deleteProcessDefinition(String id) {

        return this.instances.remove(id) != null;
    }
}
