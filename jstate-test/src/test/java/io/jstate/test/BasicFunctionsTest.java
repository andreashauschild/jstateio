package io.jstate.test;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jstate.model.configuration.ProcessTemplate;

public class BasicFunctionsTest {

    @Test
    @DisplayName("")
    void test_0001() throws IOException {


        ProcessTemplate definition = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(this.getClass().getResourceAsStream("/BasicTestProcessDefinition.json"), ProcessTemplate.class);




    }
}
