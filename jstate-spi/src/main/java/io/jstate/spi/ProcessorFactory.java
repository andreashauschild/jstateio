package io.jstate.spi;

import java.util.Map;
import java.util.Optional;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.ProcessorDefinition;

public interface ProcessorFactory {

    Optional<Processor> create(ProcessorDefinition definition);
}
