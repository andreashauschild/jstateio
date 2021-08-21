package io.jstate.core.services.io.jstate.core;

import io.jstate.model.configuration.ProcessorDefinition;
import io.jstate.spi.Processor;
import io.jstate.spi.ProcessorFactory;

import java.util.Optional;

public class DefaultProcessorFactory implements ProcessorFactory {


    @Override
    public Optional<Processor> create(ProcessorDefinition definition) {
        try {
            Processor o = (Processor) Class.forName(definition.getClazz()).getDeclaredConstructor().newInstance();
            return Optional.of(o);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
