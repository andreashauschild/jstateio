package io.jstate.core.services.io.jstate.core.state;

import static io.jstate.spi.DefaultStates.FINAL;
import static io.jstate.spi.DefaultStates.NEW;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import io.jstate.spi.State;

public class FinalState extends State {

    public FinalState(Map<String, String> props) {

        setBegin(LocalDateTime.now());
        setName(FINAL);
        setId(FINAL);
        if (props != null) {
            getProperties().putAll(props);
        }
        setEnd(LocalDateTime.now());
    }

    public FinalState() {

        this(new HashMap<>());

    }

}
