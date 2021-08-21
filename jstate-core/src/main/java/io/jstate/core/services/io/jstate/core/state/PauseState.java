package io.jstate.core.services.io.jstate.core.state;

import io.jstate.spi.State;

import java.time.LocalDateTime;
import java.util.Map;

import static io.jstate.spi.DefaultStates.CLOSED;
import static io.jstate.spi.DefaultStates.PAUSED;

public class PauseState extends State {

    private final String PROP_MESSAGE = PAUSED + ".reason";

    public PauseState(String reason, Map<String, String> properties) {

        setBegin(LocalDateTime.now());
        setName(PAUSED);
        setId(PAUSED);
        getProperties().put(PROP_MESSAGE, reason);
        if (properties != null) {
            getProperties().putAll(properties);
        }
        setEnd(LocalDateTime.now());
    }

}
