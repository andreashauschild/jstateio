package io.jprocess.mapper;

import io.jprocess.hibernate.entities.ProcessInstanceEntity;
import io.jprocess.hibernate.entities.ProcessTemplateEntity;
import io.jprocess.hibernate.entities.StateEntity;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.State;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import javax.inject.Inject;

@Mapper(config = MapperConfiguration.class, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ProcessInstanceMapper {

    @Inject
    StateMapper stateMapper;

    @Mapping(target = "states", ignore = true)
    public abstract ProcessInstanceEntity toEntity(ProcessInstance model);

    @AfterMapping
    protected void toEntityAfterMapping(ProcessInstance model, @MappingTarget ProcessInstanceEntity entity) {
        entity.persistAndFlush();
        ProcessTemplateEntity template = ProcessTemplateEntity.findById(model.getProcessTemplateId());
        entity.setProcessTemplate(template);
        for (State state : model.getStates()) {
            StateEntity stateEntity = stateMapper.toEntity(state);
            stateEntity.setProcessInstance(entity);
            entity.getStates().add(stateEntity);
        }
    }

    @Mapping(target = "states", ignore = true)
    @Mapping(source = "processTemplate.id", target = "processTemplateId")
    public abstract ProcessInstance toModel(ProcessInstanceEntity entity);

    @AfterMapping
    protected void toModelAfterMapping(ProcessInstanceEntity entity, @MappingTarget ProcessInstance model) {
        for (StateEntity stateEntity : entity.getStates()) {
            State state = stateMapper.toModel(stateEntity);
            model.getStates().add(state);
        }
    }
}
