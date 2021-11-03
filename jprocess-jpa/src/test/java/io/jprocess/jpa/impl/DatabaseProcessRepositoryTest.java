package io.jprocess.jpa.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jstate.core.services.io.jstate.core.DefaultProcessInstanceFactory;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.exception.ProcessTemplateNotExistsException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseProcessRepositoryTest {

    private static DatabaseProcessTemplateRepository databaseProcessTemplateRepository;

    private static EntityManager entityManager;
    private static ProcessTemplate processTemplate;

    private DatabaseProcessRepository subject;

    @BeforeAll
    static void beforeAll() throws IOException {
        Map<String, String> props = new HashMap<>();
        props.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:15000/jprocess");
        props.put("javax.persistence.jdbc.user", "jprocess");
        props.put("javax.persistence.jdbc.password", "Password_1");
        EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("TEST", props);

        entityManager = sessionFactory.createEntityManager();


        ProcessTemplate definition = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(DatabaseProcessRepositoryTest.class.getResourceAsStream("/BasicTestProcessDefinition.json"), ProcessTemplate.class);


        databaseProcessTemplateRepository = new DatabaseProcessTemplateRepository(entityManager);
        processTemplate = databaseProcessTemplateRepository.createProcessTemplate(definition);
    }

    @AfterAll
    static void afterAll() {

    }

    @BeforeEach
    void beforeEach() throws Exception {
        this.subject = new DatabaseProcessRepository(entityManager, new DefaultProcessInstanceFactory());
        Field f1 = this.subject.getClass().getDeclaredField("templateRepository");
        f1.setAccessible(true);
        f1.set(this.subject, databaseProcessTemplateRepository);
    }


    @Test
    @DisplayName("Test createProcessInstance happy flow.")
    void test001() {
        Map<String, String> props = new HashMap<>();
        props.put("prop1", "value1");
        props.put("prop2", "value2");
        ProcessInstance created = this.subject.createProcessInstance(UUID.randomUUID().toString(), props);
        ProcessInstance instance = this.subject.getProcessInstanceById(created.getId());
        assertEquals(created.getId(), instance.getId());
        assertEquals(2, instance.getProperties().size());
        assertEquals("value1", instance.getProperties().get("prop1"));
        assertEquals("value2", instance.getProperties().get("prop2"));
    }

    @Test
    @DisplayName("Test Exception: createProcessInstance with non existing process template.")
    void test020() {
        try {
            this.subject.createProcessInstance(UUID.randomUUID().toString(), null);
        } catch (ProcessTemplateNotExistsException e) {
            return;
        }
        fail("ProcessTemplateNotExistsException expected");
    }
    @Test
    @DisplayName("Test Exception: createProcessInstance with non existing process template.")
    void test030() {
        try {
            this.subject.createProcessInstance(UUID.randomUUID().toString(), null);
        } catch (ProcessTemplateNotExistsException e) {
            return;
        }
        fail("ProcessTemplateNotExistsException expected");
    }

}
