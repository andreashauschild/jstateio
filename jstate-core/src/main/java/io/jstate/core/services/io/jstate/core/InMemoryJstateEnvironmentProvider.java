package io.jstate.core.services.io.jstate.core;

import io.jstate.spi.JProcessService;
import io.jstate.spi.JstateValidationService;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessorFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InMemoryJstateEnvironmentProvider {

    private ProcessInstanceFactory instanceFactory;
    private InMemoryProcessTemplateRepository templateRepository;
    private InMemoryProcessRepository inMemoryProcessRepository;
    private DefaultJProcessService processService;
    private DefaultJProcessUtilService processUtilService;
    private ProcessorFactory processorFactory;
    private DefaultProcessExecutor processExecutor;
    private JstateValidationService validationService;
    private ExecutorService executor;

    private InMemoryJstateEnvironmentProvider() {

        executor = Executors.newFixedThreadPool(10);

        instanceFactory = new DefaultProcessInstanceFactory();

        templateRepository = new InMemoryProcessTemplateRepository();

        inMemoryProcessRepository = new InMemoryProcessRepository(templateRepository, instanceFactory);

        processorFactory = new DefaultProcessorFactory();

        validationService = new DefaultJstateValidationService(templateRepository);

        processExecutor = new DefaultProcessExecutor();

        processService = new DefaultJProcessService();

        processUtilService = new DefaultJProcessUtilService();

        processExecutor.setProcessorFactory(this.processorFactory);
        processExecutor.setProcessRepository(inMemoryProcessRepository);
        processExecutor.setProcessService(this.processService);
        processExecutor.setTmplRepo(templateRepository);

        processService.setProcessExecutor(processExecutor);
        processService.setProcessRepository(inMemoryProcessRepository);
        processService.setTemplateRepository(templateRepository);
        processService.setValidationService(validationService);

        processUtilService.setProcessRepository(inMemoryProcessRepository);
        processUtilService.setValidationService(validationService);

    }

    public static InMemoryJstateEnvironmentProvider getInstance() {

        return InstanceHolder.INSTANCE;
    }

    public io.jstate.spi.ProcessRepository getProcessRepository() {

        return this.inMemoryProcessRepository;
    }

    public io.jstate.spi.ProcessTemplateRepository getProcessTemplateRepository() {

        return this.templateRepository;
    }

    public ProcessInstanceFactory getProcessInstanceFactory() {

        return this.instanceFactory;
    }

    public io.jstate.spi.ProcessorFactory getProcessorFactory() {

        return null;
    }

    public ExecutorService getExecutorService() {

        return executor;
    }

    public JstateValidationService getJstateValidationService() {

        return new DefaultJstateValidationService(templateRepository);
    }

    public JProcessService getJProcessService() {

        return processService;
    }

    private static final class InstanceHolder {

        static final InMemoryJstateEnvironmentProvider INSTANCE = new InMemoryJstateEnvironmentProvider();
    }

    public DefaultJProcessUtilService getProcessUtilService() {
        return processUtilService;
    }
}
