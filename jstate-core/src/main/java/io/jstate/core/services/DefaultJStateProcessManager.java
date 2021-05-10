package io.jstate.core.services;

import java.util.List;
import java.util.Map;

import io.jstate.api.services.JStateProcessManager;
import io.jstate.core.services.io.jstate.core.InMemoryJstateEnvironmentProvider;
import io.jstate.core.services.io.jstate.core.query.GetAllUnReserved;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.JstateEnvironmentProvider;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.Processor;

/**
 * Default implementation of {@link JStateProcessManager}
 */
public class DefaultJStateProcessManager implements JStateProcessManager {

    JstateEnvironmentProvider env = InMemoryJstateEnvironmentProvider.getInstance();

    public DefaultJStateProcessManager() {

        env.getExecutorService().execute(() -> {
            while (true) {
                try {
                    System.out.println("Check Instances");
                    List<ProcessInstance> processInstance = env.getProcessRepository().findProcessInstance(new GetAllUnReserved());
                    for (ProcessInstance pro : processInstance) {
                        ProcessTemplate processTemplate = env.getProcessTemplateRepository().getProcessTemplate(pro.getProcessTemplateId());
                        env.getExecutorService().execute(() -> {
                            try {
//                                for (String processor : processTemplate.getProcessors()) {
//                                    Processor o = (Processor) Class.forName(processor).getDeclaredConstructor().newInstance();
//                                    o.process(pro);
//                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });

                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ProcessInstance newProcessInstance(String templateId, Map<String, String> initialProperties) {

        ProcessTemplate processTemplate = env.getProcessTemplateRepository().getProcessTemplate(templateId);

        return env.getProcessRepository().createProcessInstance(processTemplate.getId(), initialProperties);

    }

    @Override
    public Process reserve(String id) {

        return null;
    }
}
