package io.jstate.core.services.io.jstate.core;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessDefinitionQuery;
import io.jstate.spi.ProcessTemplateRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.jstate.core.services.io.jstate.core.ObjectUtil.cloneObject;

public class InMemoryProcessTemplateRepository implements ProcessTemplateRepository {

    private Map<String, ProcessTemplate> instances = new HashMap<>();

    @Override
    public ProcessTemplate updateProcessTemplate(String id, ProcessTemplate definition) {

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
    public ProcessTemplate getProcessTemplate(String id) {

        return cloneObject(this.instances.get(id));
    }

    @Override
    public ProcessTemplate createProcessTemplate(ProcessTemplate processTemplate) {

        String uuid = UUID.randomUUID().toString();
        this.instances.put(uuid, cloneObject(processTemplate));
        return getProcessTemplate(uuid);
    }

    @Override
    public List<ProcessTemplate> findProcessTemplate(ProcessDefinitionQuery query) {

        return null;
    }

    @Override
    public boolean deleteProcessTemplate(String id) {

        return this.instances.remove(id) != null;
    }
}
