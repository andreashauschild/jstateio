package io.jstate.core.services.io.jstate.core;

import io.jstate.model.configuration.ProcessorDefinition;
import io.jstate.spi.ProcessorFactory;

import java.lang.reflect.InvocationTargetException;

public class DefaultProcessorFactory implements ProcessorFactory {

    @Override
    public <T> T create(ProcessorDefinition definition, Class<T> type) {

        try {
            return (T) Class.forName(definition.getClazz()).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // TODO exception
            e.printStackTrace();
            return null;
        }
    }
}
