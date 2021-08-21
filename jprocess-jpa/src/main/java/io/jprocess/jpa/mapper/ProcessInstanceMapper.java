package io.jprocess.jpa.mapper;


import io.jprocess.jpa.entity.ProcessInstanceEntity;
import io.jprocess.jpa.entity.StateEntity;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.State;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(config = MapperConfiguration.class, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ProcessInstanceMapper extends BaseMapper {

    StateMapper stateMapper = Mappers.getMapper(StateMapper.class);

    @Mapping(target = "states", ignore = true)
    public abstract ProcessInstanceEntity toEntity(ProcessInstance model);

    @AfterMapping
    protected void toEntityAfterMapping(ProcessInstance model, @MappingTarget ProcessInstanceEntity entity) {
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
