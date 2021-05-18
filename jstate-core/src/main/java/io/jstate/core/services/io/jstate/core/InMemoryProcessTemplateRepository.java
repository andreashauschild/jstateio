package io.jstate.core.services.io.jstate.core;

import static io.jstate.core.services.io.jstate.core.misc.JStateUtil.cloneObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessDefinitionQuery;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.exception.ProcessTemplateNotExistsException;

public class InMemoryProcessTemplateRepository implements ProcessTemplateRepository {

    private final Map<String, ProcessTemplate> instances = new HashMap<>();

    @Override
    public ProcessTemplate updateProcessTemplate(final String id, final ProcessTemplate definition) {

        if (id == null) {
            throw new IllegalArgumentException("Error: Missing id");
        }

        if (definition == null) {
            throw new IllegalArgumentException("Error: Missing definition");
        }

        if (this.instances.containsKey(id)) {
            this.instances.put(id, definition);
            return cloneObject(this.instances.get(id));
        }
        throw new ProcessTemplateNotExistsException(id);

    }

    @Override
    public ProcessTemplate getProcessTemplate(final String id) {

        if (this.instances.containsKey(id)) {
            return cloneObject(this.instances.get(id));
        }
        throw new ProcessTemplateNotExistsException(id);
    }

    @Override
    public ProcessTemplate createProcessTemplate(final ProcessTemplate processTemplate) {

        this.instances.put(processTemplate.getId(), cloneObject(processTemplate));
        return getProcessTemplate(processTemplate.getId());
    }

    @Override
    public List<ProcessTemplate> findProcessTemplate(final ProcessDefinitionQuery query) {

        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteProcessTemplate(final String id) {

        return this.instances.remove(id) != null;
    }
}
