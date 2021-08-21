package io.jprocess.jpa.impl;

import io.jprocess.jpa.dao.ProcessTemplateDAO;
import io.jprocess.jpa.entity.ProcessTemplateEntity;
import io.jprocess.jpa.mapper.ProcessTemplateMapper;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessDefinitionQuery;
import io.jstate.spi.ProcessTemplateRepository;
import org.mapstruct.factory.Mappers;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class DatabaseProcessTemplateRepository implements ProcessTemplateRepository {

    EntityManager entityManager;

    private ProcessTemplateDAO processTemplateDAO;

    ProcessTemplateMapper templateMapper = Mappers.getMapper(ProcessTemplateMapper.class);

    public DatabaseProcessTemplateRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.processTemplateDAO = new ProcessTemplateDAO(this.entityManager);
    }

    @Override
    public ProcessTemplate updateProcessTemplate(String id, ProcessTemplate definition) {
        // TODO with transaction
        return null;
    }

    @Override
    public ProcessTemplate getProcessTemplate(String id) {
        ProcessTemplateEntity processTemplateEntity = this.processTemplateDAO.get(UUID.fromString(id)).orElse(null);
        return templateMapper.toModel(processTemplateEntity);
    }

    @Override
    public ProcessTemplate createProcessTemplate(ProcessTemplate processTemplate) {
        this.entityManager.getTransaction().begin();
        ProcessTemplateEntity processTemplateEntity = this.templateMapper.toEntity(processTemplate);
        this.processTemplateDAO.save(processTemplateEntity);
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
        return templateMapper.toModel(processTemplateEntity);
    }

    @Override
    public List<ProcessTemplate> findProcessTemplate(ProcessDefinitionQuery query) {
        return null;
    }

    @Override
    public boolean deleteProcessTemplate(String id) {
        this.processTemplateDAO.delete(UUID.fromString(id));
        return true;
    }
}
