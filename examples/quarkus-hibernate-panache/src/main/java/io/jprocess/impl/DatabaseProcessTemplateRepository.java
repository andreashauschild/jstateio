package io.jprocess.impl;

import io.jprocess.hibernate.entities.ProcessTemplateEntity;
import io.jprocess.mapper.ProcessTemplateMapper;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessDefinitionQuery;
import io.jstate.spi.ProcessTemplateRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class DatabaseProcessTemplateRepository implements ProcessTemplateRepository {

    @Inject
    ProcessTemplateMapper templateMapper;

    @Override
    public ProcessTemplate updateProcessTemplate(String id, ProcessTemplate definition) {
        return null;
    }

    @Override
    public ProcessTemplate getProcessTemplate(String id) {
        return null;
    }

    @Override
    public ProcessTemplate createProcessTemplate(ProcessTemplate processTemplate) {
        ProcessTemplateEntity processTemplateEntity = this.templateMapper.toEntity(processTemplate);
        processTemplateEntity.persist();
        return templateMapper.toModel(processTemplateEntity);
    }

    @Override
    public List<ProcessTemplate> findProcessTemplate(ProcessDefinitionQuery query) {
        return null;
    }

    @Override
    public boolean deleteProcessTemplate(String id) {
        return false;
    }
}
