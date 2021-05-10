package io.jstate.spi;

import io.jstate.model.configuration.ProcessTemplate;

import java.util.Map;

public interface ProcessInstanceFactory {

    ProcessInstance create(ProcessTemplate template, Map<String, String> initialProperties);
}
