package io.jstate.spi;

import java.util.List;

import io.jstate.model.configuration.ProcessTemplate;

public interface ProcessTemplateRepository {

    ProcessTemplate updateProcessTemplate(String id, ProcessTemplate definition);

    ProcessTemplate getProcessTemplate(String id);

    ProcessTemplate createProcessTemplate(ProcessTemplate processTemplate);

    List<ProcessTemplate> findProcessTemplate(ProcessDefinitionQuery query);

    boolean deleteProcessTemplate(String id);

}
