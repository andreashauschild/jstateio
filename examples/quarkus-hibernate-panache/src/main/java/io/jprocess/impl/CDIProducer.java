package io.jprocess.impl;

import io.jstate.core.services.io.jstate.core.DefaultJProcessService;
import io.jstate.core.services.io.jstate.core.DefaultJstateValidationService;
import io.jstate.core.services.io.jstate.core.DefaultProcessExecutor;
import io.jstate.core.services.io.jstate.core.DefaultProcessInstanceFactory;
import io.jstate.spi.JProcessService;
import io.jstate.spi.JstateValidationService;
import io.jstate.spi.ProcessExecutor;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessorFactory;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

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
    JProcessService produceJProcessService() {
        return new DefaultJProcessService()
                .setProcessExecutor(produceProcessExecutor())
                .setProcessRepository(databaseProcessRepository)
                .setTemplateRepository(databaseProcessTemplateRepository)
                .setProcessExecutor(produceProcessExecutor())
                .setExecutor(managedExecutor)
                .setValidationService(produceJstateValidationService());
    }

    @Produces
    @ApplicationScoped
    JstateValidationService produceJstateValidationService() {
        return new DefaultJstateValidationService(databaseProcessTemplateRepository);
    }

    @Produces
    @ApplicationScoped
    ProcessExecutor produceProcessExecutor() {
        return new DefaultProcessExecutor().setProcessorFactory(produceProcessorFactory()).setProcessService(produceJProcessService());
    }


}
