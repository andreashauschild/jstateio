package io.jstate.core.services.io.jstate.core;

import io.jstate.spi.JstateEnvironmentProvider;
import io.jstate.spi.JstateValidationService;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.ProcessorFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InMemoryJstateEnvironmentProvider implements JstateEnvironmentProvider {

    private final InMemoryProcessInstanceFactory instanceFactory;
    private final InMemoryProcessTemplateRepository templateRepository;
    private final InMemoryProcessRepository processRepository;
    ExecutorService executor;

    private static final class InstanceHolder {

        static final InMemoryJstateEnvironmentProvider INSTANCE = new InMemoryJstateEnvironmentProvider();
    }

    private InMemoryJstateEnvironmentProvider() {

        executor = Executors.newFixedThreadPool(10);

        instanceFactory = new InMemoryProcessInstanceFactory();

        templateRepository = new InMemoryProcessTemplateRepository();

        processRepository = new InMemoryProcessRepository(templateRepository, instanceFactory);
    }

    public static InMemoryJstateEnvironmentProvider getInstance() {

        return InstanceHolder.INSTANCE;
    }

    @Override
    public ProcessRepository getProcessRepository() {

        return this.processRepository;
    }

    @Override
    public ProcessTemplateRepository getProcessTemplateRepository() {

        return this.templateRepository;
    }

    @Override
    public ProcessInstanceFactory getProcessInstanceFactory() {

        return this.instanceFactory;
    }

    @Override
    public ProcessorFactory getProcessorFactory() {

        return null;
    }

    @Override
    public ExecutorService getExecutorService() {

        return executor;
    }

    @Override
    public JstateValidationService getJstateValidationService() {

        return new DefaultJstateValidationService(templateRepository);
    }

}
