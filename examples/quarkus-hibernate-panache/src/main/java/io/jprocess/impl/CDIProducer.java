package io.jprocess.impl;

import io.jprocess.impl.DatabaseProcessRepository;
import io.jprocess.impl.DatabaseProcessTemplateRepository;
import io.jstate.core.services.io.jstate.core.DefaultJProcessService;
import io.jstate.core.services.io.jstate.core.DefaultJstateValidationService;
import io.jstate.core.services.io.jstate.core.DefaultProcessExecutor;
import io.jstate.core.services.io.jstate.core.DefaultProcessInstanceFactory;
import io.jstate.core.services.io.jstate.core.DefaultProcessorFactory;
import io.jstate.spi.JProcessService;
import io.jstate.spi.JstateEnvironmentProvider;
import io.jstate.spi.JstateValidationService;
import io.jstate.spi.ProcessExecutor;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.ProcessorFactory;
import io.vertx.core.impl.VertxImpl;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.concurrent.ExecutorService;

public class CDIProducer {

    @Inject
    ManagedExecutor managedExecutor;


    @Inject
    DatabaseProcessRepository databaseProcessRepository;

    @Inject
    DatabaseProcessTemplateRepository databaseProcessTemplateRepository;

    @Produces
    @ApplicationScoped
    ProcessInstanceFactory produceProcessInstanceFactory() {
        return new DefaultProcessInstanceFactory();
    }

    @Produces
    @ApplicationScoped
    ProcessorFactory produceProcessorFactory() {
        return new CDIProcessorFactory();
    }

    @Produces
    @ApplicationScoped
    JstateValidationService produceJstateValidationService() {
        return new DefaultJstateValidationService(databaseProcessTemplateRepository);
    }

    @Produces
    @ApplicationScoped
    ProcessExecutor produceProcessExecutor() {
        return new DefaultProcessExecutor().setProcessorFactory(produceProcessorFactory()).setProcessService();
    }

    @ApplicationScoped
    JstateEnvironmentProvider produceJstateEnvironmentProvider() {
        return new JstateEnvironmentProvider() {

            @Override
            public ProcessRepository getProcessRepository() {
                return databaseProcessRepository;
            }

            @Override
            public ProcessTemplateRepository getProcessTemplateRepository() {
                return databaseProcessTemplateRepository;
            }

            @Override
            public ProcessInstanceFactory getProcessInstanceFactory() {
                return produceProcessInstanceFactory();
            }

            @Override
            public ProcessorFactory getProcessorFactory() {
                return produceProcessorFactory();
            }

            @Override
            public ExecutorService getExecutorService() {
                return managedExecutor;
            }

            @Override
            public JstateValidationService getJstateValidationService() {
                return produceJstateValidationService();
            }

            @Override
            public JProcessService getJProcessService() {
                DefaultJProcessService processService = new DefaultJProcessService();
                processService.setProcessExecutor(managedExecutor);
                processService.setProcessRepository(databaseProcessRepository);
                processService.setTemplateRepository(databaseProcessTemplateRepository);
                processService.setValidationService(validationService);
                return null;
            }
        }

    }
}
