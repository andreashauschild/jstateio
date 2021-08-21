package io.jstate.core.services.io.jstate.core.state;

import io.jstate.core.services.io.jstate.core.misc.JStateUtil;
import io.jstate.spi.DefaultStates;
import io.jstate.spi.State;

import java.time.LocalDateTime;
import java.util.Map;

import static io.jstate.spi.DefaultStates.ERROR;

public class ErrorState extends State {

    private final String PROP_ERROR_MESSAGE = ERROR + ".reason";

    public ErrorState(Throwable e, Map<String, String> properties) {

        setBegin(LocalDateTime.now());
        setName(ERROR);
        setId(ERROR);
        getProperties().put(PROP_ERROR_MESSAGE, JStateUtil.toString(e));
        if (properties != null) {
            getProperties().putAll(properties);
        }
        setEnd(LocalDateTime.now());
    }

    public ErrorState(String reason, Map<String, String> properties) {

        setBegin(LocalDateTime.now());
        setName(ERROR);
        setId(ERROR);
        getProperties().put(PROP_ERROR_MESSAGE, reason);
        if (properties != null) {
            getProperties().putAll(properties);
        }
        setEnd(LocalDateTime.now());
    }

}
