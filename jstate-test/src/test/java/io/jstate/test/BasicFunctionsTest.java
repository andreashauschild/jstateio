package io.jstate.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.jstate.core.services.DefaultJStateProcessManager;
import io.jstate.core.services.io.jstate.core.DefaultJstateEnvironmentProvider;
import io.jstate.spi.JstateEnvironmentProvider;

import static io.jstate.spi.DefaultStates.NEW;
import static io.jstate.test.BasicFunctionTestProcessor.CLEANUP_WORKING_DIR;
import static io.jstate.test.BasicFunctionTestProcessor.COPY_IMAGES_TO_WORKING_DIR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasicFunctionsTest {

    private DefaultJStateProcessManager manager = new DefaultJStateProcessManager();

    JstateEnvironmentProvider env = DefaultJstateEnvironmentProvider.getInstance();
    private ProcessTemplate testTemplate;

    @BeforeEach
    void beforeEach() throws Exception {

        ProcessTemplate definition = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(this.getClass().getResourceAsStream("/BasicTestProcessDefinition.json"), ProcessTemplate.class);

        this.testTemplate = env.getProcessTemplateRepository().createProcessTemplate(definition);
    }

    @Test
    @DisplayName("Create new process instance from template")
    void test_0001() throws IOException, InterruptedException {

        Map<String, String> props = new HashMap<>();
        props.put("prop1", "value1");
        ProcessInstance instance = env.getJstateService().newProcessInstance(testTemplate.getId(), props);

        assertEquals(1, instance.getStates().size());
        assertEquals(1, instance.getProperties().size());
        assertEquals("value1", instance.getProperties().get("prop1"));
        assertEquals(NEW, instance.getCurrentState().getId());
        assertNotNull(instance.getProcessTemplateId());
        assertNotNull(instance.getCreated());
        assertNotNull(instance.getLastUpdate());
    }

    @Test
    @DisplayName("Transition of states")
    void test_0010() throws IOException, InterruptedException {

        Map<String, String> props = new HashMap<>();
        props.put("prop1", "value1");
        ProcessInstance instance = env.getJstateService().newProcessInstance(testTemplate.getId(), props);
        ProcessInstance reserved = env.getJstateService().reserveProcessInstance(instance.getId());

        Map<String, String> props2 = new HashMap<>();
        props.put("prop1", "new value");
        ProcessInstance updated = env.getJstateService().transition(reserved.getReservationId(), state(COPY_IMAGES_TO_WORKING_DIR, props2));

        assertEquals(2, updated.getStates().size());
        assertEquals(1, updated.getProperties().size());
        assertEquals("value1", updated.getProperties().get("prop1"));
        assertEquals(COPY_IMAGES_TO_WORKING_DIR, updated.getCurrentState().getId());
        assertNotNull(updated.getCurrentState().getBegin());
        assertNotNull(updated.getCurrentState().getEnd());
        assertNotNull(updated.getLastUpdate());
    }

    @Test
    @DisplayName("Transition fails because of missing reservation")
    void test_0020() throws IOException, InterruptedException {

        Map<String, String> props = new HashMap<>();
        props.put("prop1", "value1");
        ProcessInstance instance = env.getJstateService().newProcessInstance(testTemplate.getId(), props);
        ProcessInstance reserved = env.getJstateService().reserveProcessInstance(instance.getId());

        Map<String, String> props2 = new HashMap<>();
        props.put("prop1", "new value");
        ProcessInstance updated = env.getJstateService().transition(reserved.getReservationId(), state(COPY_IMAGES_TO_WORKING_DIR, props2));

        assertEquals(2, updated.getStates().size());
        assertEquals(1, updated.getProperties().size());
        assertEquals("value1", updated.getProperties().get("prop1"));
        assertEquals(COPY_IMAGES_TO_WORKING_DIR, updated.getCurrentState().getId());
        assertNotNull(updated.getCurrentState().getBegin());
        assertNotNull(updated.getCurrentState().getEnd());
        assertNotNull(updated.getLastUpdate());
    }

    private State state(String id, Map<String, String> props) {

        State state = new State().setId(id);
        state.setProperties(props);
        return state;
    }

}
