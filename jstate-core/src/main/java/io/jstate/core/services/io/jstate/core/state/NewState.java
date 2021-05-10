package io.jstate.core.services.io.jstate.core.state;

import static io.jstate.spi.DefaultStates.NEW;

import java.time.LocalDateTime;
import java.util.Map;

import io.jstate.core.services.io.jstate.core.misc.JStateUtil;
import io.jstate.spi.State;

public class NewState extends State {

    private final String PROP_ERROR_MESSAGE = NEW + ".error";

    public NewState(Map<String,String> intialProperties) {

        setBegin(LocalDateTime.now());
        setName(NEW);
        setId(NEW);
        if(intialProperties!=null){
            getProperties().putAll(intialProperties);
        }
        setEnd(LocalDateTime.now());
    }

    public NewState(String errorMessage) {

        setBegin(LocalDateTime.now());
        setName(NEW);
        setId(NEW);
        getProperties().put(PROP_ERROR_MESSAGE, errorMessage);
        setEnd(LocalDateTime.now());
    }

}
