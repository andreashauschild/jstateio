package io.jprocess.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jprocess.test.DockerDatabaseTestResource;
import io.jprocess.test.LocalDatabaseTestResource;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.JProcessService;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessTemplateRepository;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

import static io.jprocess.processors.BasicFunctionTestProcessor.CLEANUP_WORKING_DIR;
import static io.jprocess.processors.BasicFunctionTestProcessor.COPY_IMAGES_TO_WORKING_DIR;
import static io.jprocess.processors.BasicFunctionTestProcessor.CREATE_MPEG_FROM_IMAGES;
import static io.jprocess.processors.BasicFunctionTestProcessor.UPLOAD_MPEG_TO_SERVER;
import static io.jstate.spi.DefaultStates.FINAL;
import static io.jstate.spi.DefaultStates.NEW;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;


@QuarkusTestResource(LocalDatabaseTestResource.class)
@QuarkusTest
class JProcessResourceTest {
    @Inject
    JProcessService subject;

    @Inject
    ProcessTemplateRepository processTemplateRepository;


    @DisplayName("Create Process: Happy Flow")
    @Test
    void test_0001() {
        ProcessTemplate processTemplate = insertProcessTemplate("BasicTestProcessDefinition.json");
        ProcessInstance processInstance = this.subject.create(processTemplate.getId(), Map.of("prop1", "val1", "prop2", "val2"));

        assertEquals(processTemplate.getId(), processInstance.getProcessTemplateId());
        assertEquals(processInstance.getCurrentState().getName(), NEW);
        assertEquals(1, processInstance.getStates().size());
        assertEquals(2, processInstance.getStates().get(0).getProperties().size());
        assertEquals("val1", processInstance.getStates().get(0).getProperties().get("prop1"));
        assertEquals("val2", processInstance.getStates().get(0).getProperties().get("prop2"));
    }

    @DisplayName("Execute Process: Happy Flow")
    @Test
    void test_0002() {
        ProcessTemplate processTemplate = insertProcessTemplate("BasicTestProcessDefinition.json");
        ProcessInstance instance = this.subject.create(processTemplate.getId(), Map.of("prop1", "val1", "prop2", "val2"));
        ProcessInstance execute = this.subject.executeSync(instance.getId());

        assertEquals(6, execute.getStates().size());
        assertEquals(NEW, execute.getStates().get(0).getName());
        assertEquals(COPY_IMAGES_TO_WORKING_DIR, execute.getStates().get(1).getName());
        assertEquals(CREATE_MPEG_FROM_IMAGES, execute.getStates().get(2).getName());
        assertEquals(UPLOAD_MPEG_TO_SERVER, execute.getStates().get(3).getName());
        assertEquals(CLEANUP_WORKING_DIR, execute.getStates().get(4).getName());
        assertEquals(FINAL, execute.getStates().get(5).getName());
    }


    private ProcessTemplate insertProcessTemplate(String name) {
        try {
            String template = new String(this.getClass().getResourceAsStream("/process-templates/" + name).readAllBytes(), UTF_8);
            return processTemplateRepository.createProcessTemplate(new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(template, ProcessTemplate.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
