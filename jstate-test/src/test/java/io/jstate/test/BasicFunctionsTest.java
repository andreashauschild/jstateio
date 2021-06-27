package io.jstate.test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jstate.core.services.io.jstate.core.InMemoryJstateEnvironmentProvider;
import io.jstate.core.services.io.jstate.core.state.NewState;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.State;
import io.jstate.spi.exception.AlreadyReservedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.jstate.spi.DefaultStates.ERROR;
import static io.jstate.spi.DefaultStates.FINAL;
import static io.jstate.spi.DefaultStates.NEW;
import static io.jstate.test.BasicFunctionTestProcessor.CLEANUP_WORKING_DIR;
import static io.jstate.test.BasicFunctionTestProcessor.COPY_IMAGES_TO_WORKING_DIR;
import static io.jstate.test.BasicFunctionTestProcessor.CREATE_MPEG_FROM_IMAGES;
import static io.jstate.test.BasicFunctionTestProcessor.UPLOAD_MPEG_TO_SERVER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class BasicFunctionsTest {


    InMemoryJstateEnvironmentProvider env = InMemoryJstateEnvironmentProvider.getInstance();
    private ProcessTemplate testTemplate;

    @BeforeEach
    void beforeEach() throws Exception {

        ProcessTemplate definition = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(this.getClass().getResourceAsStream("/BasicTestProcessDefinition.json"), ProcessTemplate.class);

        this.testTemplate = env.getProcessTemplateRepository().createProcessTemplate(definition);
    }

    @Test
    @DisplayName("Create new process instance from template")
    void test_0001() throws Exception {

        Map<String, String> props = new HashMap<>();
        props.put("prop1", "value1");
        ProcessInstance instance = env.getJProcessService().create(testTemplate.getId(), props);

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
    void test_0010() throws Exception {

        Map<String, String> props = new HashMap<>();
        props.put("prop1", "value1");
        ProcessInstance instance = env.getJProcessService().create(testTemplate.getId(), props);
        ProcessInstance reserved = env.getJProcessService().reserve(instance.getId());

        Map<String, String> props2 = new HashMap<>();
        props.put("prop1", "new value");
        ProcessInstance updated = env.getJProcessService().transition(reserved.getReservationId(), state(COPY_IMAGES_TO_WORKING_DIR, props2));

        assertEquals(2, updated.getStates().size());
        assertEquals(1, updated.getProperties().size());
        assertEquals("value1", updated.getProperties().get("prop1"));
        assertEquals(COPY_IMAGES_TO_WORKING_DIR, updated.getCurrentState().getId());
        assertNotNull(updated.getCurrentState().getBegin());
        assertNotNull(updated.getCurrentState().getEnd());
        assertNotNull(updated.getLastUpdate());
    }

    @Test
    @DisplayName("Reservation fails because process has already a reservation")
    void test_0020() throws Exception {

        Map<String, String> props = new HashMap<>();
        props.put("prop1", "value1");
        ProcessInstance instance = env.getJProcessService().create(testTemplate.getId(), props);

        env.getJProcessService().reserve(instance.getId());
        try {
            env.getJProcessService().reserve(instance.getId());
            fail("AlreadyReservedException expected");
        } catch (Exception e) {
            assertTrue(e instanceof AlreadyReservedException);
        }

    }

    @Test
    @DisplayName("Force transition")
    void test_0030() throws Exception {

        Map<String, String> props = new HashMap<>();
        props.put("prop1", "value1");
        ProcessInstance instance = env.getJProcessService().create(testTemplate.getId(), props);
        ProcessInstance reserve = env.getJProcessService().reserve(instance.getId());
        ProcessInstance forceTransition = env.getJProcessService().forceTransition(reserve.getReservationId(), new NewState());
        assertEquals(2, forceTransition.getStates().size());
        assertEquals(NEW, forceTransition.getStates().get(0).getId());

    }

    @Test
    @DisplayName("execute processor")
    void test_0040() throws Exception {

        Map<String, String> props = new HashMap<>();
        props.put("prop1", "value1");
        ProcessInstance instance = env.getJProcessService().create(testTemplate.getId(), props);

        Thread.sleep(100);
        ProcessInstance execute = env.getJProcessService().executeSync(instance.getId());
        assertEquals(6, execute.getStates().size());
        assertEquals(NEW, execute.getStates().get(0).getName());
        assertEquals(COPY_IMAGES_TO_WORKING_DIR, execute.getStates().get(1).getName());
        assertEquals(CREATE_MPEG_FROM_IMAGES, execute.getStates().get(2).getName());
        assertEquals(UPLOAD_MPEG_TO_SERVER, execute.getStates().get(3).getName());
        assertEquals(CLEANUP_WORKING_DIR, execute.getStates().get(4).getName());
        assertEquals(FINAL, execute.getStates().get(5).getName());
    }

    @Test
    @DisplayName("Transition toError")
    void test_0050() throws Exception {

        Map<String, String> props = new HashMap<>();
        props.put("prop1", "error");
        ProcessInstance instance = env.getJProcessService().create(testTemplate.getId(), new HashMap<>());
        ProcessInstance reserve = env.getJProcessService().reserve(instance.getId());
        ProcessInstance toError = env.getJProcessService().toError(reserve.getReservationId(), "This is a error", props);
        assertEquals(2, toError.getStates().size());
        assertEquals(ERROR, toError.getStates().get(1).getId());

        assertEquals("error", toError.getProperties().get("prop1"));

    }


    private State state(String id, Map<String, String> props) {

        State state = new State().setId(id);
        state.setProperties(props);
        return state;
    }

}
