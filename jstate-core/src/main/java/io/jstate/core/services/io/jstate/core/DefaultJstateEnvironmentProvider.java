package io.jstate.core.services.io.jstate.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.jstate.spi.JstateEnvironmentProvider;
import io.jstate.spi.JProcessService;
import io.jstate.spi.JstateValidationService;
import io.jstate.spi.ProcessExecutor;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessorFactory;

public class DefaultJstateEnvironmentProvider implements JstateEnvironmentProvider {

    private ProcessInstanceFactory instanceFactory;
    private InMemoryProcessTemplateRepository templateRepository;
    private InMemoryProcessRepository inMemoryProcessRepository;
    private DefaultJProcessService processService;
    private ProcessorFactory processorFactory;
    private DefaultProcessExecutor processExecutor;
    private JstateValidationService validationService;
    private ExecutorService executor;

    private DefaultJstateEnvironmentProvider() {

        executor = Executors.newFixedThreadPool(10);

        instanceFactory = new DefaultProcessInstanceFactory();

        templateRepository = new InMemoryProcessTemplateRepository();

        inMemoryProcessRepository = new InMemoryProcessRepository(templateRepository, instanceFactory);

        processorFactory = new DefaultProcessorFactory();

        validationService = new DefaultJstateValidationService(templateRepository);

        processExecutor = new DefaultProcessExecutor();

        processService = new DefaultJProcessService();

        processExecutor.setProcessorFactory(this.processorFactory);
        processExecutor.setProcessService(this.processService);
        processExecutor.setTmplRepo(templateRepository);

        processService.setProcessExecutor(processExecutor);
        processService.setProcessRepository(inMemoryProcessRepository);
        processService.setTemplateRepository(templateRepository);
        processService.setValidationService(validationService);

    }

    public static DefaultJstateEnvironmentProvider getInstance() {

        return InstanceHolder.INSTANCE;
    }

    @Override
    public io.jstate.spi.ProcessRepository getProcessRepository() {

        return this.inMemoryProcessRepository;
    }

    @Override
    public io.jstate.spi.ProcessTemplateRepository getProcessTemplateRepository() {

        return this.templateRepository;
    }

    @Override
    public ProcessInstanceFactory getProcessInstanceFactory() {

        return this.instanceFactory;
    }

    @Override
    public io.jstate.spi.ProcessorFactory getProcessorFactory() {

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

    @Override
    public JProcessService getJProcessService() {

        return processService;
    }

    private static final class InstanceHolder {

        static final DefaultJstateEnvironmentProvider INSTANCE = new DefaultJstateEnvironmentProvider();
    }

}
