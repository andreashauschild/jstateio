package io.jprocess.mapper;

import io.jprocess.hibernate.entities.ProcessInstanceEntity;
import io.jstate.spi.ProcessInstance;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ProcessInstanceMapper {

    @Mapping(target = "processTemplateId", ignore = true)
    public abstract ProcessInstanceEntity toEntity(ProcessInstance processInstance);

    @AfterMapping
    protected  ProcessInstanceEntity toEntityAfterMapping(ProcessInstance processInstance){

    }
}
