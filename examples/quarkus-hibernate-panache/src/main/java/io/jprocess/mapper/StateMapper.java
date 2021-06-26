package io.jprocess.mapper;

import io.jprocess.hibernate.entities.StateEntity;
import io.jprocess.hibernate.entities.StatePropertiesEntity;
import io.jstate.spi.State;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.Map;

@Mapper(config = MapperConfiguration.class, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class StateMapper {

    @Mapping(target = "properties", ignore = true)
    public abstract StateEntity toEntity(State model);

    @AfterMapping
    protected void toEntityAfterMapping(State model, @MappingTarget StateEntity entity) {
        for (Map.Entry<String, String> entry : model.getProperties().entrySet()) {
            StatePropertiesEntity prop = new StatePropertiesEntity(entry.getKey(), entry.getValue());
            prop.setState(entity);
            entity.getProperties().add(prop);
        }
    }


}
