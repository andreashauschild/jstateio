package io.jstate.core.services.io.jstate.core.state;

import static io.jstate.spi.DefaultStates.CLOSED;
import static io.jstate.spi.DefaultStates.ERROR;

import java.time.LocalDateTime;
import java.util.Map;

import io.jstate.spi.State;

public class CloseState extends State {

    private final String PROP_MESSAGE = CLOSED + ".reason";

    public CloseState(String reason, Map<String, String> properties) {

        setBegin(LocalDateTime.now());
        setName(CLOSED);
        setId(CLOSED);
        getProperties().put(PROP_MESSAGE, reason);
        if (properties != null) {
            getProperties().putAll(properties);
        }
        setEnd(LocalDateTime.now());
    }

}
