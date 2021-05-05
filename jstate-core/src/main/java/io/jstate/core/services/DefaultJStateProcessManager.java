package io.jstate.core.services;

import java.util.Map;

import io.jstate.api.services.JStateProcessManager;
import io.jstate.core.services.io.jstate.core.InMemoryProcessRepository;
import io.jstate.core.services.io.jstate.core.InMemoryProcessTemplateRepository;
import io.jstate.core.services.io.jstate.core.ProcessInstanceFactory;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;

/**
 * Default implementation of {@link JStateProcessManager}
 */
public class DefaultJStateProcessManager implements JStateProcessManager {

    ProcessInstanceFactory factory = new ProcessInstanceFactory();

    InMemoryProcessTemplateRepository definitionRepo = new InMemoryProcessTemplateRepository();
    InMemoryProcessRepository processesRepo = new InMemoryProcessRepository(definitionRepo, factory);

    public ProcessInstance newProcessInstance(String templateId, Map<String, String> initialProperties) {

        ProcessTemplate processTemplate = definitionRepo.getProcessTemplate(templateId);
        return null;
    }

    @Override
    public Process reserve(String id) {

        return null;
    }
}
