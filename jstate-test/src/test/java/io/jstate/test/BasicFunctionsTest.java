package io.jstate.test;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.jstate.core.services.DefaultJStateProcessManager;
import io.jstate.core.services.io.jstate.core.InMemoryJstateEnvironmentProvider;
import io.jstate.spi.JstateEnvironmentProvider;

public class BasicFunctionsTest {

    private DefaultJStateProcessManager manager = new DefaultJStateProcessManager();

    JstateEnvironmentProvider env = InMemoryJstateEnvironmentProvider.getInstance();

    @Test
    @DisplayName("")
    void test_0001() throws IOException, InterruptedException {

        // ProcessTemplate definition = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        // .readValue(this.getClass().getResourceAsStream("/BasicTestProcessDefinition.json"), ProcessTemplate.class);
        //
        // ProcessTemplate processTemplate = env.getProcessTemplateRepository().createProcessTemplate(definition);
        //
        // ProcessInstance processInstance = manager.newProcessInstance(definition.getId(), Map.of("prop1","val1"));
        // System.out.println(processInstance);
        //
        // Thread.sleep(5000);
    }
}
