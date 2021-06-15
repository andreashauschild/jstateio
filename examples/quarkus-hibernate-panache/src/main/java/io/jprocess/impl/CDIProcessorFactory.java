package io.jprocess.impl;

import io.jstate.model.configuration.ProcessorDefinition;
import io.jstate.spi.ProcessorFactory;
import io.quarkus.arc.Arc;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CDIProcessorFactory implements ProcessorFactory {

    @Override
    public <T> Optional<T> create(ProcessorDefinition definition, Class<T> type) {
        try {
            ;
            if (Arc.container().select(Class.forName(definition.getClazz())).isResolvable()) {
                return Optional.of((T) Arc.container().select(Class.forName(definition.getClazz())).get());
            } else {
                return Optional.of((T) Class.forName(definition.getClazz()).getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
