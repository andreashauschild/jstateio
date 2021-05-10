package io.jstate.spi;

import java.util.Map;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.ProcessorDefinition;

public interface ProcessorFactory {

    <T> T create(ProcessorDefinition definition, Class<T> type);
}
