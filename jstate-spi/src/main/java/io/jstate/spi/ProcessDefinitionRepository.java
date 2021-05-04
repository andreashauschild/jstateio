package io.jstate.spi;

import java.util.List;

import io.jstate.model.configuration.ProcessDefinition;

public interface ProcessDefinitionRepository {

    ProcessDefinition updateProcessDefinition(String id, ProcessDefinition definition);

    ProcessDefinition getProcessDefinition(String id);

    ProcessDefinition createProcessDefinition(ProcessDefinition processDefinition);

    List<ProcessDefinition> findProcessDefinition(ProcessDefinitionQuery query);

    boolean deleteProcessDefinition(String id);

}
