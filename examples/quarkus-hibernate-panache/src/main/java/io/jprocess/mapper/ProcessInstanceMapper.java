package io.jprocess.mapper;

import io.jprocess.hibernate.entities.ProcessInstanceEntity;
import io.jprocess.hibernate.entities.StateEntity;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.State;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import javax.inject.Inject;

@Mapper(config = MapperConfiguration.class,unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ProcessInstanceMapper {

    @Inject
    StateMapper stateMapper;

    @Mapping(target = "states", ignore = true)
    public abstract ProcessInstanceEntity toEntity(ProcessInstance model);

    @AfterMapping
    protected void toEntityAfterMapping(ProcessInstance model, @MappingTarget ProcessInstanceEntity entity) {
        for (State state : model.getStates()) {
            StateEntity stateEntity = stateMapper.toEntity(state);
            entity.getStates().add(stateEntity);
        }
    }

    @Mapping(target = "states", ignore = true)
    public abstract ProcessInstance toModel(ProcessInstanceEntity entity);

    @AfterMapping
    protected void toModelAfterMapping(ProcessInstanceEntity entity, @MappingTarget ProcessInstance model) {
        for (State state : model.getStates()) {
            StateEntity stateEntity = stateMapper.toEntity(state);
            entity.getStates().add(stateEntity);
        }
    }
}
