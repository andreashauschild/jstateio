package io.jstate.core.services.io.jstate.core;

import java.util.Optional;

import io.jstate.model.configuration.ProcessorDefinition;
import io.jstate.spi.ProcessorFactory;

public class DefaultProcessorFactory implements ProcessorFactory {

    @Override
    public <T> Optional<T> create(ProcessorDefinition definition, Class<T> type) {

        try {
            return Optional.of((T) Class.forName(definition.getClazz()).getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
