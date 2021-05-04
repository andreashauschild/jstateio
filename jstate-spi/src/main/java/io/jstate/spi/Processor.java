package io.jstate.spi;

public interface Processor {
    
    State process(ProcessInstance processInstance);
}
