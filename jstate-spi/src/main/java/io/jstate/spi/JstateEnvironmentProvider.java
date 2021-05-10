package io.jstate.spi;

import java.util.concurrent.ExecutorService;

public interface JstateEnvironmentProvider {

    ProcessRepository getProcessRepository();

    ProcessTemplateRepository getProcessTemplateRepository();

    ProcessInstanceFactory getProcessInstanceFactory();

    ProcessorFactory getProcessorFactory();

    ExecutorService getExecutorService();
}
